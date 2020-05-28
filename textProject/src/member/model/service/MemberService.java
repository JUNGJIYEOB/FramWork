package member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Check;
import member.model.vo.Member;
import member.model.vo.MemberData;
import member.model.vo.Search;

public class MemberService {

	public Member selectMember(Member m) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member member = new MemberDao().selectOneMember(session, m);
		session.close();

		return member;
	}

	public int inserMember(Member m) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new MemberDao().inserMember(session, m);
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public ArrayList<Member> selectAllMember() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().selectAllMember(session);
		session.close();

		return (ArrayList<Member>) list;
	}

	public int updateMember(Member m) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new MemberDao().updateMember(session, m);
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;

	}

	public int deleteMember(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = new MemberDao().deleteMember(session, memberId);
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();

		return result;
	}

	public MemberData selectAllMemberPage(int reqPage) {
		// 한 페이지당 게시물수 numPerPage->개발자 지정
		// 총 게시물수 totalCount->DB 조회
		SqlSession session = SqlSessionTemplate.getSqlSession();
		// 해당 페이지 게시물 시작 번호,끝번호 Start/End -> totalPage,reqPage 로 계산
		int totalCount = new MemberDao().totalCount(session);
		// 페이징 길이 PageNaviSize->개발자 지정
		System.out.println("totalCount:" + totalCount);

		// 한 페이지당 게시물 수
		int numPerPage = 5;
		// 총 페이지 수
		int totalPage = 0;
		// 토타 카운트가 2개

		// 나머지가 생기면 한페이지 더 줘야 하니깐 +1 ~!
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		// 조회해올 게시물 시작번호
		int start = (reqPage - 1) * numPerPage + 1;
		int end = reqPage * numPerPage;
		/*
		 * 1.페이지 게시물 5 1페이지 rnum 1~5 까지 필요하고 2페이지 rnum 6~10
		 * 
		 * 1페이지
		 */

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);

		List list = new MemberDao().selectAllMemberPage(session, map);
		// 페이지 네비 게이션 작성 시작
		String pageNavi = "";
		// 페이지 네비 길이
		int pageNaviSize = 3;
		// v페이지 네비 시작번호 연산
		// 1~3 페이지 요청 1로 시작 , 4~6 페이지 요청시 4로 시작...
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		// 페이지 네비는 [이전] [페이지 번호][다음] 순서로 작성
		if (pageNo != 1) {
			pageNavi += "<a href='/allMemberPage?reqPage=" + (pageNo - 1) + "'>[이전]</a>";

		}
		for (int i = 0; i < pageNaviSize; i++) {
			if (reqPage == pageNo) {
				pageNavi += "<span>" + pageNo + "</span>";

			} else {
				pageNavi += "<a href='/allMemberPage?reqPage=" + pageNo + "'>" + pageNo + "</a>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		if(pageNo<=totalPage) {
			pageNavi +="<a href='/allMemberPage?reqPage=?"+pageNo+"'>[다음]</a>";
		}
		MemberData md = new MemberData((ArrayList<Member>)list,pageNavi);
		session.close();
		return md;
	}

	public ArrayList<Member> memberInfo(Check ck) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().memberInfo(session,ck);
		session.close();
		return (ArrayList<Member>)list;
	}

	public ArrayList<Member> search(Search search) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session,search);
		session.close();
		return (ArrayList<Member>)list;
	}

	public ArrayList<Member> search2(Member m) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session,m);
		session.close();
		return (ArrayList<Member>)list;
	}

	public ArrayList<Member> search(String[] memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session,memberId);
		session.close();
		return (ArrayList<Member>)list;
	}

}
