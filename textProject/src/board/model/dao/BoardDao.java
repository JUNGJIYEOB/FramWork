package board.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.model.vo.Board;

public class BoardDao {

	public int totalCount(SqlSession session, HashMap<String, String> map) {
		return session.selectOne("board.totalCount", map);
	}

	public List selectList(SqlSession session, HashMap<String, String> map) {
		return session.selectList("board.selectList", map);
	}

	public int insertBoard(SqlSession session, Board b) {
		return session.insert("board.insertBoard",b);
	}

	public Board selectOneBoard(SqlSession session, int boardNo) {
		return session.selectOne("board.selectOneBoard", boardNo);
	}

	public int addCount(SqlSession session, int boardNo) {
		return session.update("board.addCount",boardNo);
	}

	public int updateBoard(SqlSession session, Board b) {
		return session.update("board.updateBoard",b);
	}

//	public int deleteBoard(SqlSession session, int boardNo) {
//		return session.delete("board.deleteBoard", boardNo);
//	}

	public int deleteBoard(SqlSession session, String[] delNo) {
		return session.delete("board.deleteBoard2", delNo);
	}

}
