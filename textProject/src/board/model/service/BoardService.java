package board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardData;
import common.SqlSessionTemplate;

public class BoardService {

	public BoardData selectList(int reqPage, String type, String keyword) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		//페이지당 게시물 수
		int numPerPage = 10;
		
		//totalCount 구하기
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("keyword", keyword);		
		int totalCount = new BoardDao().totalCount(session, map);
		
		//전체 페이지 수 구하기
		int totalPage;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		
		//게시물 시작값, 끝값 조회\
		int start = (reqPage-1)*numPerPage + 1;
		int end = reqPage * numPerPage;
		
		//게시물 구해오기(검색값 옵션을 넣을때도 활용하기 위함)
		map.put("start", String.valueOf(start));
		map.put("end", String.valueOf(end));
		List list = new BoardDao().selectList(session, map);
		
		//페이지 네비
		String pageNavi = "";
		int pageNaviSize = 3; //페이지 번호 길이
		
		//pageNo 연산 -> 페이지 시작번호
		//int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		int pageNo = 1;
		if(reqPage!=1) {
			pageNo = reqPage-1;
		}
		
		//이전버튼 생선
		if(pageNo != 1) {
			pageNavi += "<a href='/boardList?reqPage="+ (pageNo-1);
			if(type != null) {
				pageNavi += "&type=" + type + "&keyword=" + keyword;	
			}
			pageNavi += "'>이전</a>";
		}
		
		//DB 게시물 50개 입력 후 COMMIT
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<span>"+pageNo+"</span>";
			}else {
				pageNavi += "<a href='/boardList?reqPage="+pageNo;
				if(type != null) {
					pageNavi += "&type=" + type + "&keyword=" + keyword;	
				}
				pageNavi += "'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<a href='/boardList?reqPage="+pageNo;
			if(type != null) {
				pageNavi += "&type=" + type + "&keyword=" + keyword;	
			}
			pageNavi += "'>다음</a>";
		}
		
		session.close();
		
		BoardData bd = new BoardData((ArrayList<Board>)list, pageNavi);		
		
		return bd;
	}

	public int insertBoard(Board b) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new BoardDao().insertBoard(session,b);
		
		if(result>0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Board b = new BoardDao().selectOneBoard(session,boardNo);
		int result = new BoardDao().addCount(session, boardNo);
		if(result>0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return b;
	}

	public int updateBoard(Board b) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new BoardDao().updateBoard(session, b);
		
		if(result>0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

//	public int deleteBoard(int boardNo) {
//		SqlSession session = SqlSessionTemplate.getSqlSession();
//		int result = new BoardDao().deleteBoard(session, boardNo);
//		
//		if(result>0) {
//			session.commit();
//		}else {
//			session.rollback();
//		}
//		session.close();
//		return result;
//	}

	public int deleteBoard(String[] delNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new BoardDao().deleteBoard(session, delNo);
		if(result>0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

}
