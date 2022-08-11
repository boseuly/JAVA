package com.myhome.web.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDAO;

@Service
public class EmpService {

	@Autowired
	private EmpDAO dao;
	
	@Transactional
	public Paging getPage(int page, int limit) { // 현재 몇 페이지고, 몇 행씩 나오게 할지
		// 총 몇 행인지 알아야 한다.
		int totalRows = dao.totalRow();
		Paging paging = new Paging(page, limit, totalRows);
		dao.searchPage(paging); // 여기서 setPageDatas 설정
		
		return paging;
	}
/*	
	public List<Integer> getPageList(int pageCount) {
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		
		return pageList;

	}
	
	public EmpDTO getId(String empId) {
		if(!(empId.matches("\\d+"))){
			return null;
		}
		EmpDTO data = dao.getId(Integer.parseInt(empId));
		return data;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDetailDTO data = dao.getDetail(empId); // 해당 직원에 대한 상세 정보 가져오기
		
		return data;
	}

	public String getProfileImage(HttpServletRequest request, String imagePath, EmpDTO empData) {
		//페이지에 대한 서블릿 실행 환경 정보를 담고 있는 application 내장 객체를 리턴한다.
		// 내가 매개변수로 전달해준 imagePath 경로는 그냥 프로그램상 경로 -> 실제경로 X
		String realPath = request.getServletContext().getRealPath(imagePath);  // imagePath의 실제 경로 찾기
		File file = new File(realPath + empData.getEmpId() + ".png"); // 이미지명은 empId.png 으로 저장하기로 했으니까
		
		// 파일 
		if(file.exists()) { // 만약 해당 경로에 그 이미지가 있다면
			return imagePath + empData.getEmpId() + ".png";
		}else {
			return imagePath + "default.png";
		}
		
	}
	
	public String getProfileImage(HttpServletRequest request, String imagePath, int id) {
		data.setEmpId(id);
		return getProfileImage(request, imagePath, data);
	}

	public boolean setEmployee(EmpDTO updateEmpData, EmpDetailDTO updateEmpDetailData) {
		// 데이터를 저장할 거임
		
		// 이메일 형식
		String email = updateEmpData.getEmail(); // 이슈!! 이메일이 존재하지 않아서 자꾸 nullpointexception
		if(email.contains("@emp.com")) { // 만약 이걸 포함하고 있다면 추가할 때는 없애고 넣어야 한다.
			email = email.replace("@emp.com", ""); // 빈문자열로 바꿔준다.
			updateEmpData.setEmail(email);
		}
		boolean res1 = dao.updateEmployee(updateEmpData); // emp를 저장
		boolean res2 = dao.updateEmployeeDetail(updateEmpDetailData); // empDetail 저장
		
		if(res1 && res2) {
			return true;
		}
		
		return false;
	}


*/


}
