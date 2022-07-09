package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.noticeDTO;

@WebServlet("/notice/detail")
public class NoticeDetaliControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
	    
		String url="jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String sql="SELECT * FROM NOTICE WHERE ID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "puser1", "puser1");
			PreparedStatement st = con.prepareStatement(sql);	// preparedStatement 는 미리 쿼리문을 준비한다.
			st.setInt(1, id);	// 1번째 ? 에 id값을 넣겠다.

			ResultSet rs = st.executeQuery();
			rs.next();	//다음 레코드를 가지고 오세요

			 
			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("REGDATE");
			String writerId = rs.getString("WRITER_ID");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");
			
			noticeDTO notice = new noticeDTO(id, title,writerId, content, regdate, hit, files);
			
			request.setAttribute("notice", notice);
			request.setAttribute("title", title);
			request.setAttribute("writerId", writerId);
			request.setAttribute("regdate", regdate);	      
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
		   
			
			rs.close();
		    st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// redirect : 아예 다른 페이지로 이동하기 
		
		// forward : 여기서 작업했던 내용들을 다른 서버에서 이어나가고자할 때
		
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
