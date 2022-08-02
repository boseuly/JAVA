package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.EmpBoardService;

@WebServlet("/ajax/board/del")
public class AjaxBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 기능은 post만 있어도 됨
		response.setContentType("application/json; chrset=utf-8");
		String id = request.getParameter("id");
		// 불러온 id에 해당하는 게시물을 삭제 해야 한다.
		EmpBoardService boardService = new EmpBoardService();
		boolean result = boardService.boardDelete(Integer.parseInt(id));
		
		
		StringBuilder sb = new StringBuilder();	// 문자열로 보내려고 만든 거임
		sb.append("{");
		
		PrintWriter out = response.getWriter();
		if(result) { // 만약 삭제에 성공했다면 
			sb.append(String.format("\"%s\": \"%s\",", "type", "success"));
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 성공"));
			sb.append(String.format("\"%s\": \"%s\",", "message", "게시물을 삭제 하였습니다."));
		}else { // 삭제 실패
			sb.append(String.format("\"%s\": \"%s\",", "type", "fail"));
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
			sb.append(String.format("\"%s\": \"%s\",", "message", "해당 게시물을 삭제하는 중 문제가 발생하였습니다."));
		}
		sb.append("}");
		out.print(sb.toString());
		out.flush();
		
	}

}
