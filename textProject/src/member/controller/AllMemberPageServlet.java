package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.MemberData;

/**
 * Servlet implementation class AllMemberPageServlet
 */
@WebServlet(name = "AllMemberPage", urlPatterns = { "/allMemberPage" })
public class AllMemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMemberPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.변수에 저장
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e){ //정수값이 안넘어올 때
			reqPage = 1;
		}
		//3.비즈니스로직
		MemberData md = new MemberService().selectAllMemberPage(reqPage);
		
		//4.결과처리
		request.setAttribute("list", md.getList());
		request.setAttribute("pageNavi", md.getPageNavi());
		request.getRequestDispatcher("/WEB-INF/views/allMemberPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
