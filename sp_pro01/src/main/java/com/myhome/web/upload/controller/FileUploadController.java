package com.myhome.web.upload.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.service.BoardService;
import com.myhome.web.upload.model.FileUploadDTO;
import com.myhome.web.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/upload")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadService uploadService;
	
	@Autowired
	private BoardService boardService; // bId 찾을 때 필요
	

	// 이미지 저장하기
	@PostMapping(value="/image", produces="application/json; charset=utf-8")
	@ResponseBody
	public String image(Model model, HttpServletRequest request , HttpSession session 
			, @RequestParam("upload") MultipartFile[] files // servlet에서 getPart를 사용했던 것과 같음
			, @RequestParam("type") String type) throws Exception { 
		logger.info("image(file={})", files);
		
		String realPath = request.getServletContext().getRealPath("/resources"); // static의 진짜 파일위치 찾기
		JSONObject json = new JSONObject();
		for(MultipartFile file : files) { // 파일이 없을 때까지 반복
		/*
			System.out.println(realPath);
			System.out.println("isEmpty() : " + file.isEmpty());
			System.out.println("getName() : " + file.getName());
			System.out.println("getOriginalFilename() : " + file.getOriginalFilename()); // 파일의 진짜 이름
			System.out.println("getSize() : " + file.getSize() / 1000);  	// 기본 byte로 나온다. -> 1키로바이트로 출력하려면 1000을 나눈다.
		*/
			json.put("uploaded", 1);
			json.put("fileName", file.getOriginalFilename()); // 파일의 진짜 이름을 보낸다.
			json.put("url", request.getContextPath() + "/static/img/board/" + file.getOriginalFilename()); // 저장할 때는 realPath로 저장을 해야하지만 파일을 불러올 때는 contextPath로 프로그램에서 가져와야 한다.
			file.transferTo(new File(realPath + "/img/board/" + file.getOriginalFilename()));	// 어디에 저장할지를 파일 객체 이용해서 지정한다.
			// 파일 입출력 -> throws 필요
			// 어디에 저장되어 있는지 경로를 알려줘야 ckeditor가 이미지를 띄워줄 수 있다.

			
			// upload테이블에 저장하기 - bId, fileName, loacation, url, fileSize 필요
			FileUploadDTO fileData = new FileUploadDTO();
			fileData.setFileName(file.getOriginalFilename());
			fileData.setLocation(realPath);
			fileData.setUrl(request.getContextPath() + "/static/img/board" + file.getOriginalFilename());
			fileData.setFileSize(file.getSize()/1000);
			
			// 세션이 null일 수도 있음 -> 상세 페이지로 이동하지 않고 바로 추가 버튼을 누른 경우 bId세션은 null]
			// 이 경우에는 nextSeq()를 통해서 다음 boardId를 찾아서 넣어준다.
			int bId = 0;
			if(session.getAttribute("bId") == null) {
				bId = boardService.nextSeq();
			}else { // 만약 bId가 있다면
				bId = (int)session.getAttribute("bId");
			}
			fileData.setbId(bId);
			
			
			// fileData의 정보를 테이블에 저장해야 한다.
			boolean result = uploadService.updateFile(fileData); 
			if (!result) { 
				// 파일 데이터 저장 실패시 -> 저장 실패하였다고 메시지 전달
				model.addAttribute("error", "file을 저장하는 과정에서 알 수 없는 오류가 발생하였습니다.");
				
				return "error/failSave";
			}
		}
		return json.toJSONString().toString();
	}

	// 파일 리스트 보여주기 
	
}
