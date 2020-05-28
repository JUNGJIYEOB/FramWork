package member.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.model.vo.Check;
import member.model.vo.Member;
import member.model.vo.Search;

public class MemberDao {

	public Member selectOneMember(SqlSession session, Member m) {
		/*
		 * member->이게 구분자 :mapper 의 namespace 값 찾아감/ 실행항 쿼리의 id 값 ->select의 id 값 찾아감
		 *
		 */
		return session.selectOne("member.selectOneMember", m);
	}

	public int inserMember(SqlSession session, Member m) {

		return session.insert("member.insertMember", m);
	}

	public List selectAllMember(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("member.selectAllMember");
	}

	public int updateMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.update("member.updateMember", m);
	}

	public int deleteMember(SqlSession session, String memberId) {
		return session.delete("member.deleteMember", memberId);
	}

	public int totalCount(SqlSession session) {

		return session.selectOne("member.totalCount");
	}

	public List selectAllMemberPage(SqlSession session, HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return session.selectList("member.allMemberListPage", map);
	}

	public List memberInfo(SqlSession session, Check ck) {
		// TODO Auto-generated method stub
		return session.selectList("member.memberInfo", ck);
	}

	public List search(SqlSession session, Search search) {
		// TODO Auto-generated method stub
		return session.selectList("member.search", search);
	}

	public List search(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.selectList("member.search2", m);
	}

	public List search(SqlSession session, String[] memberId) {
		// 배열을 매개변수호 사용하는 경우
		// HashMap<String,String[]>map =new HashMap<String,String[]>()'
		// map.put("array",뱌열객);
		// 리스트인경우
		// map.put("list",리스트객체)
		return session.selectList("member.search3", memberId);
	}

}
