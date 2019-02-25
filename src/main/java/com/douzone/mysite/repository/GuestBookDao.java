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

import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.mysite.vo.UserVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	public GuestBookVo get(long no) {
		GuestBookVo vo = sqlSession.selectOne("guestbook.getNo",no);
		return vo;
//		Connection conn = null;
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		 GuestBookVo vo = new GuestBookVo();
//		try {
//			 conn = dataSource.getConnection();
//			 
//			 String sql = "select no , name  , message,reg_date from guestbook where no=?";
//			 pstmt = conn.prepareStatement(sql);
//			
//			 pstmt.setLong(1, no);
//			 rs = pstmt.executeQuery();
//			 while(rs.next())
//			 {
//				 String name = rs.getString(2);
//				 String message = rs.getString(3);
//				 String date = rs.getString(4);
//				 vo.setNo(no);
//				 vo.setName(name);
//				 vo.setDate(date);
//				 vo.setMessage(message);
//			 }
//			 return vo;
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
//		return null;
	}
	
	public List<GuestBookVo> getList()
	{
		 List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
				 return list;
	}
	
//	public List<GuestBookVo> getList(int page)
//	{
//		
//			List<GuestBookVo> list = new ArrayList<GuestBookVo>();
//		
//		
//		Connection conn = null;
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		try {
//			
//			int sqlPage = page-1;
//			sqlPage *= 5;
//			
//			
//			 conn = getConnection();
//			 
//			 //password는 쿼리에 안쓰는게 좋음 .. 메모리에 올라가면 보안관련 문제가 있기때문
//			 String sql = "select no , name , password , message,reg_date from guestbook order by no desc limit ?,5";
//			 pstmt = conn.prepareStatement(sql);
//			 
//			 //바인딩
//			 pstmt.setInt(1, sqlPage);
//			 rs = pstmt.executeQuery();
//			 while(rs.next())
//			 {
//				 long no = rs.getLong(1);
//				 String name = rs.getString(2);
//				 String password = rs.getString(3);
//				 String message = rs.getString(4);
//				 String date = rs.getString(5);
//				 
//				 GuestBookVo vo = new GuestBookVo();
//				 vo.setNo(no);
//				 vo.setName(name);
//				 vo.setPassword(password);
//				 vo.setDate(date);
//				 vo.setMessage(message);
//				 list.add(vo);
//			 }
//			 return list;
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
//		
//		
//		
//	}
//	
//	
	
	
	public Long insert(GuestBookVo vo)
	{
		sqlSession.insert("guestbook.insert", vo);
		long no = vo.getNo();
		return no;
	}

	public void delete(String no,String password)
	{
		
		Map<String,String> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		sqlSession.delete("guestbook.delete", map);
	}
}


