package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.noticeDTO;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<noticeDTO> list = new ArrayList<noticeDTO>();
		
		String url="jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String sql="SELECT * FROM NOTICE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "puser1", "puser1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				noticeDTO notice = new noticeDTO(id, title,writerId, content, (java.sql.Date) regdate, hit, files);
				
				list.add(notice);
			
			}
		
			
			rs.close();
			st.close();
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
