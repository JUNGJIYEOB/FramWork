package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardData;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet(name = "BoardList", urlPatterns = { "/boardList" })
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.변수저장
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String type= request.getParameter("type");
		String keyword =request.getParameter("keyword");
		//3.비지니스로직
		BoardData bd = new BoardService().selectList(reqPage,keyword,type);
		//4.결과처리
		request.setAttribute("reqPage", reqPage);
		request.setAttribute("list",bd.getList());
		request.setAttribute("pageNavi", bd.getPageNavi());
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
