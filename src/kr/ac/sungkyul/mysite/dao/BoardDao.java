package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.mysite.vo.BoardVo;

public class BoardDao {
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void update(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			Long no = vo.getNo();
			String title = vo.getTitle();
			String content = vo.getContent();
			
			String sql = "update BOARD set title=?, content=? where no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, no);
			
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(pstmt != null){
					pstmt.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Long no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			String sql = "delete from board where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public BoardVo getBoard(Long no){
		BoardVo vo = new BoardVo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();

			String sql = "select no, title, content, user_no "
					+ "		from BOARD "
					+ "		where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				Long boardNo = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userNo = rs.getLong(4);
				
				vo = new BoardVo();
				vo.setNo(boardNo);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userNo);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(rs != null){
					rs.close();
				}
				
				if(pstmt != null){
					pstmt.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public boolean insert(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try{
			conn = getConnection();
			
			String sql = "insert into BOARD "
					+ "		values(seq_board.nextval, ?, ?, sysdate, 0, "
					+ "			  nvl((select max(GROUP_NO) from BOARD), 1)+1, 1, 1, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());
			
			count = pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			try{
				if(pstmt != null){
					pstmt.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return (count == 1);
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select b.no, b.title, b.content, "
					+ "			 to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss'),"
					+ "			 b.view_count, u.NAME, b.user_no "
					+ "		from board b, users u "
					+ "		where b.USER_NO = u.NO "
					+ "		order by b.REG_DATE desc";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				Integer viewCount = rs.getInt(5);
				String writer = rs.getString(6);
				Long userNo = rs.getLong(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setViewCount(viewCount);
				vo.setWriter(writer);
				vo.setUserNo(userNo);
				
				list.add(vo);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(rs != null){
					rs.close();
				}
				
				if(stmt != null){
					stmt.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
