package com.myhome.web.board.controller;

import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.upload.model.FileUploadDTO;
import com.myhome.web.upload.service.FileUploadService;


@Controller
@RequestMapping(value="/board") // 모든 메소드를 board로 매핑한다. 
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;

	@Autowired
	private CommentService commentService;
	@Autowired
	private FileUploadService fileService;
	
	// 조회
	@RequestMapping(value="", method=RequestMethod.GET) // 정보만 가져다 줄 거니까 GET
	public String getData(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page 			// 몇 페이지인지 받아온다.
			, @RequestParam(defaultValue="0", required=false) int pageCount) { 	// 몇 행 나오게 할 것인지 
		logger.info("getData(page={},pageCount={})",page, pageCount);
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5); // 이전에 저장한 session이 없을 수도 있으니까 미리 초기화해둔다.
		}
		
		// pageCount를 변경하고자 요청한 경우
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);  
		}
		
		Paging pageData =  service.getPage(page, Integer.parseInt(session.getAttribute("pageCount").toString())); // session은 object로 반환되기 
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("datas", pageData.getPageDatas());
		
		
		return "board/list"; // 이건 jsp url 주소임
	}
	
	// 조회 상세		
	/* 
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public String getDetail(Model model
			,@PathVariable int id) // @PathVariable을 사용하면 ?를 사용하지 않아도 된다. // path로 설정해두는 것이다.
	*/
	// 아래 방법보다 위에 방법이 요즘 트렌드임
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String getDetail(Model model, HttpSession session // FileUploadController에서 loadDTO에 파일 정보 저장하려면 bId가 필요한데 얻을 방법이 없음
			, @RequestParam int id 		// bId
			, @RequestParam(defaultValue="1") int page	// 댓글 page // 만약 page가 null이라면 기본적으로 1페이지로 설정
			, @SessionAttribute("loginData") EmpDTO empDto) { 
		// limit(pageCount)는 기본적으로 5로 설정
		int limit = 5;
		
		BoardDTO data = service.getData(id);
		Paging commentPage = commentService.getCommentPage(page, limit, id); // 댓글 페이징 데이터 
		
		// 파일 저장할 때 사용할 세션
		// 만약 세션이 존재한다면 세션을 삭제 해준다.
		if(session.getAttribute("bId") != null) {
			session.removeAttribute("bId"); // bId 속성을 삭제
		}
		session.setAttribute("bId", data.getId()); // 세션에 bId를 새롭게 넣어준다.
		
		// 저장한 파일 리스트가 떠야 한다.
		List<FileUploadDTO> fileList = fileService.getFileList(data.getId()); // bId에 맞는 파일 리스트를 불러와라
		model.addAttribute("files", fileList);
		
		if(data == null) {
			model.addAttribute("error", "해당 데이터는 존재하지 않습니다.");
			return "error/noExists";
		}else {
			service.incViewCnt(empDto, data); // 로그인한 사람 정보랑 게시판 정보 전달 -> 해당 게시판 방문 기록 추가
			model.addAttribute("data", data);
			model.addAttribute("commentPage", commentPage);
			return "board/detail";
		}
	}
	
	//추가 폼 요청 -> 폼을 보여주는 것은 모델이 필요하다. 
	@GetMapping(value="/add") // 정보만 가져다 줄 거니까 GET
	public String add(@SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
		logger.info("add(empDto={})", empDto);
		return "board/add";
	}
	
	// 추가 저장 요청
	@PostMapping(value="/add")
	public String add(@ModelAttribute BoardVO boardVo
			, Model model, HttpServletRequest request
			, @RequestParam("uploadFiles") MultipartFile[] files // 저장한 파일 가져오기
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto) { // session에 있는 loginData 속성을 가지고 오는 것, session이름이랑 매개변수 이름이랑 매치가 되어야 한다. -> 만약 매치가 안 되면 name="loginData" 이런식으로 해줘야 함 
		logger.info("add(boardVo={}, empDto={})", boardVo, empDto);
		// EmpDTO empDto = (EmpDTO)session.getAttribute("loginData");
		// 위에서 @SessionAttribute를 했기 때문에 굳이 로직에서 session.getAttribute하지 않아도 된다.
		logger.info("image(file={})", files);
		
		
		int id = service.add(empDto, boardVo); // 게시글을 저장하고 bid를 얻어온다.
		String realPath = request.getServletContext().getRealPath("/resources"); // 실제 위치 찾기
		
		FileUploadDTO fileData = new FileUploadDTO();
		fileData.setbId(id);
		
		for(MultipartFile file : files) {
			// 파일의 url, 실제주소와 이름
			fileData.setFileName(file.getOriginalFilename());
			fileData.setLocation(realPath);
			fileData.setUrl(request.getContextPath() + "/static/img/board" + file.getOriginalFilename());
			fileData.setFileSize(file.getSize()/1000);
			fileService.updateFile(fileData); // 파일을 추가한다.
		}
		model.addAttribute("files", fileData);
		
		if(id > 0) {
			return "redirect:/board/detail?id=" + id; // 추가 성공시 해당 게시글 상세 페이지로 이동
		}else {
			return "board/add"; // 문제가 생기면 다시 baord/add로 forward
		}
	}
	
	// 수정 폼요청 
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			, @RequestParam int id) {
		logger.info("modify(empDto={}, id={})", empDto, id);
		
		BoardDTO data = service.getData(id);
		if(empDto.getEmpId() == data.getEmpId()) {
			model.addAttribute("data", data);
			return "board/modify";		// 만약 수정하려는 사람과 작성자가 서로 같다면
		}else {
			model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
			return "error/permission"; 	// 만약 수정하려는 사람과 작성자가 서로 다르다면
		}
	}
	
	// 수정 저장 요청
	@PostMapping(value="/modify")
	public String modify(Model model
			,@SessionAttribute(name="loginData", required=true) EmpDTO empDto
			,@ModelAttribute BoardVO boardVo) {
		BoardDTO data = service.getData(boardVo.getId()); // 게시글 데이터 조회 먼저 해준다.
		
		if(empDto.getEmpId() == data.getEmpId()) {
			data.setTitle(boardVo.getTitle());
			data.setContent(boardVo.getContent());
			boolean result = service.modifyBoard(data);
			if(result) {
				return "redirect:/board/detail?id=" + data.getId();
			}else {
				return "board/modify";
			}
		}else {
			model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
			return "error/permission";
		}
	}
	
	
	// 삭제
	@PostMapping (value="/delete", produces="application/json; charset=utf-8") // response.setContex() 했던 것처럼
	@ResponseBody // ajax를 사용할 때는 반드시 @ResponseBody 필요 응답 데이터를 전달해준다.
	public String delete(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) { // 파라미터로 들어올 아이디가 존재
		
		logger.info("delete(empDto={}, id={})", empDto, id);
		BoardDTO data = service.getData(id);
		JSONObject json = new JSONObject();
		
		if(data == null) { 
			// 이미 삭제
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다.");
			return json.toJSONString();
		}else {
			if(data.getEmpId() == empDto.getEmpId()) { // 게시글 
				boolean result = service.boardDelete(data);
				// 삭제 가능
				if(result) {
					// 삭제 성공 
					json.put("title", "삭제 완료");
					json.put("message", "삭제 처리가 완료되었습니다.");
					return json.toJSONString();
				}else {
					// 삭제 실패
					json.put("title", "삭제 실패");
					json.put("message", "삭제 중 알 수 없는 문제가 발생하였습니다.");
					return json.toJSONString();
				}
			}else {
				// 작성자 불일치 - 삭제 불가 - 권한 없음 
				json.put("title", "삭제불가");
				json.put("message", "해당 데이터를 삭제할 권한이 없습니다.");
				return json.toJSONString();
			}
		}
	}
	
	// 댓글 추가
	@PostMapping(value="/comment/add")
	public String commentAdd(Model model, HttpSession session
			, @SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int bid				// 사용자가 댓글을 달 게시글
			, @RequestParam String content) { 	// 사용자가 입력한 내용
		
		// 댓글 추가
		CommentDTO data = new CommentDTO();
		data.setbId(bid);
		data.setContent(content);
		data.setEmpId(empDto.getEmpId());
		
		try {
			commentService.add(data);
			return "redirect:/board/detail?id=" + bid; // 성공하면 그냥 게시글 보여준다.
		} catch (SQLDataException e) {
			session.setAttribute("commentError", "댓글 추가 작업 중 오류가 발생하였습니다.");
			return "redirect:/board/detail?id=" + bid;
		}
	}
	
	
}
