package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<BoardVo> getList(int page)
	{	
		page=(page-1)*10;
		List<BoardVo> list =sqlSession.selectList("board.getList",page);
		return list;
	}

	public boolean write(BoardVo vo)
	{
		sqlSession.insert("board.insert",vo);
		return false;
		
	}

	public boolean delete(String no)
	{
		boolean result = sqlSession.delete("board.delete",no) ==1;
		return result;
	}

	public boolean hit(String boardNo) {
		boolean result = sqlSession.update("board.hit",boardNo) ==1;
		return result;
	}

	public BoardVo view(long no)
	{
		return sqlSession.selectOne("board.view",no);
	}

	public boolean modify(BoardVo vo)
	{
		return sqlSession.update("board.modify",vo)==1;
	}

	public void replyWrite(BoardVo vo)
	{
		BoardVo boardVo=sqlSession.selectOne("board.replyWrite0",vo.getNo());
		vo.setDepth(boardVo.getDepth());
		vo.setoNo(boardVo.getoNo());
		vo.setgNo(boardVo.getgNo());
		sqlSession.update("board.replyWrite1",vo);
		sqlSession.insert("board.replyWrite2",vo);
	}

	public List<BoardVo> searchGetList(String text,String way,int page)
	{
		Map <String,Object> map = new HashMap<String, Object>();
		map.put("text", text);
		map.put("way", way);
		map.put("page", (page-1)*10);
		return sqlSession.selectList("board.getSearchList", map);
//		List<BoardVo> list = new ArrayList<BoardVo>();
//		Connection conn = null;
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		try {
//			 page=page-1;
//			 page=page*10;
//			 conn = getConnection();
//
//			 String sql =  "select board.no,board.title,board.contents,board.write_date,board.hit,board.g_no,board.o_no,board.depth,user.no,user.name from board,user where board.user_no = user.no and "+way+" like '%"+text+"%' order by g_no DESC, o_no ASC limit "+page+",10";
//			 pstmt = conn.prepareStatement(sql);
//			 rs = pstmt.executeQuery();
//				 while(rs.next())
//				 {
//					 long no = rs.getLong(1);
//					 String title = rs.getString(2);
//					 String contents = rs.getString(3);
//					 String writeDate = rs.getString(4);
//					 int hit = rs.getInt(5);
//					 int gNo = rs.getInt(6);
//					 int oNo = rs.getInt(7);
//					 int depth = rs.getInt(8);
//					 long userNo = rs.getLong(9);
//					 String userName = rs.getString(10);
//					 BoardVo boardVo = new BoardVo();
//					 boardVo.setNo(no);
//					 boardVo.setTitle(title);
//					 boardVo.setContents(contents);
//					 boardVo.setWriteDate(writeDate);
//					 boardVo.setHit(hit);
//					 boardVo.setgNo(gNo);
//					 boardVo.setoNo(oNo);
//					 boardVo.setDepth(depth);
//					 boardVo.setUserName(userName);
//					 boardVo.setUserNo(userNo);
//					 list.add(boardVo);			 
//				 }
//			return list;
//			 
//		} catch (SQLException e) {
//			System.out.println("error:"+e);
//		} 
//		finally 
//		{
//				try {
//					if(conn !=null)
//					conn.close();
//					if(rs !=null)
//						rs.close();
//					if(pstmt != null)
//						pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			
//		}
//		
//		
//		return list;
//		
		
		
		
	}	

	public int searchCount(String text,String way) {
		Map <String,Object> map = new HashMap<String, Object>();
		map.put("text", text);
		map.put("way", way);
		return sqlSession.selectOne("board.searchCount", map);
	}
	public int BoardCount() {
		return sqlSession.selectOne("board.boardCount");
	}

	public void commentWrite(String contents, long userNo, long boardNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("contents", contents);
		map.put("userNo", userNo);
		map.put("boardNo", boardNo);
		sqlSession.insert("commentWrite",map);
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs =null;
//		try {
//			conn = getConnection();
//
//			 String sql =  "select max(comment.o_no)+1 from board,comment where board.no="+boardNo;
//			 pstmt = conn.prepareStatement(sql);
//			 rs = pstmt.executeQuery();
//			 int oNo =0;
//			 while(rs.next())
//			 {
//				oNo = rs.getInt(1);
//			 }
//			 sql = "insert into comment values(null,?,current_timestamp(),?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, contents);
//			pstmt.setInt(2, oNo);
//			pstmt.setLong(3, boardNo);
//			pstmt.setLong(4, userNo);
//
//
//			count = pstmt.executeUpdate();
//			return count;
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if(rs !=null)
//					rs.close();
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return count;
	}

	public List<CommentVo> getCommentList(int page,String boardNo)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardNo",boardNo);
		map.put("page", (page-1)*10);
		List<CommentVo> list = sqlSession.selectList("board.commentList",map);
		return list;
		
	}
	public void commentDelete(String no)
	{
		sqlSession.delete("board.commentDelete",no);
	}

	public int commentCount(String boardNo) {
		return sqlSession.selectOne("board.commentCount",boardNo);
	}
}
