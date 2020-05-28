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
		//한 페이지당 게시물 수 numPerPage - 개발자 지정 
		//총 게시물 수  - totalCount - db조회
		//해당 페이지의 게시물 시작번호, 끝번호 - start, end -> totalPage, reqPage로 계산
		//페이징 길이 pageNaviSize - 개발자 지정
		
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int totalCount = new MemberDao().totalCount(session); //총 게시물 수
		System.out.println("totalCount : " + totalCount);
		
		int numPerPage = 5; //한 페이지당 게시물 수
		
		//총 페이지 수 구하는 연산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) { //총 게시물 수/한 페이지당 게시물 수가 딱 맞아 떨어지면
			totalPage = totalCount/numPerPage; //그 몫을 페이지 길이
		}else {
			totalPage = totalCount/numPerPage+1; //그 몫에 +1한
		}
		
		//조회해올 게시물 시작번호와 끝번호 조회
		int start = (reqPage-1)*numPerPage+1;//요청페이지번호-1 *한 페이지당 게시물 수+1
		int end = reqPage * numPerPage;
		
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List list = new MemberDao().selectAllMemberPage(session, map);
		
		//페이지 네비게이션 작성
		String pageNavi = "";
		//페이지 네비 길이 ( < 1 2 3 > 처럼 )
		int pageNaviSize = 3;
		//페이지 네비 시작번호 연산 1~3페이지 요청 시 1로 시작, 4~6페이지 요청 시 4로 시작 ( < 4 5 6 > 처럼)
		int pageNo = (reqPage-1)/pageNaviSize+1;
		// 페이지 네비는 < [페이지 번호] > 순서로 작성
		if(pageNo != 1) { //이전 버튼 생성
			pageNavi += "<a href='/allMemberPage?reqPage="+(pageNo-1)+"' >[이전]</a>";
		}
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) { //선택된 페이지 일때
				pageNavi += "<span>"+pageNo+"</span>";
			}else {
				pageNavi += "<a href='/allMemberPage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/allMemberPage?reqPage="+pageNo+"'>다음</a>";
		}
		
		MemberData md = new MemberData((ArrayList<Member>)list, pageNavi);
		session.close();
		return md;
	
	}
	public ArrayList<Member> memberInfo(Check ck) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().memberInfo(session, ck);
		session.close();
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> search(Search search) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session, search);
		session.close();
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> search2(Member m) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session, m);
		session.close();
		return (ArrayList<Member>) list;
	}

	public ArrayList<Member> search(String[] memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List list = new MemberDao().search(session, memberId);
		session.close();
		return (ArrayList<Member>) list;
	}

}
