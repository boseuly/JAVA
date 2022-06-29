package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.LocsDTO;
import dept.service.LocsService;

@WebServlet("/locsController")
public class LocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocsService service = new LocsService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		List<LocsDTO> locsDatas = null;
		
		if(search == null) { // 만약 입력값이 없다면
			locsDatas = service.getAll(); // 전체데이터를 반환해라
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) { // 만약 숫자가 맞으면
					LocsDTO data = service.getId(search); // 입력값에 맞는 id를 조회해라
					if(data != null) {										// 만약 해당 id를 가진 데이터가 있다면 리스트에 넣어준다.
							locsDatas = new ArrayList<LocsDTO>(); // ArrayList객체 생성
							locsDatas.add(data);
					}
				}
			}
		// 값을 전달할 때 object로 업캐스팅 되어서 전달되므로 getAttribute로 꺼낼 때는 다운 캐스팅을 시켜줘야 한다.
		request.setAttribute("locsDatas", locsDatas); // locsDatas라는 키로 locsDatas라는 값을 넣는다.
		String view = "WEB-INF/jsp/locs.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	
	}

}
