package bit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bit.util.db.DataBase;

public class SubboardDao extends BbsDao{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ArrayList<SubboardDto> list(BbsDto bbsBean){
		
		ArrayList<SubboardDto> list=new ArrayList<SubboardDto>();
		String sql="select * from subboard where code LIKE '"+bbsBean.getCode()+"' AND num="+bbsBean.getNum()+" AND subnum="+bbsBean.getSubnum()+" order by idx desc";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				SubboardDto bean = new SubboardDto();
				bean.setCode(rs.getString("code"));
				bean.setNum(rs.getInt("num"));
				bean.setSubnum(rs.getInt("subnum"));
				bean.setIdx(rs.getInt("idx"));
				bean.setTime(rs.getDate("time"));
				bean.setTimeDetail(rs.getTime("time"));
				bean.sethCode(rs.getString("hcode"));
				bean.setId(rs.getString("id"));
				bean.setContent(rs.getString("content"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println("subboard list() 실패");
		} finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
		return list;
	}
	public boolean add(String code, int num, int subnum, String hCode, String id, String content) {
		String subquery="select max(idx) as idx from subboard where code LIKE '"+code+"' AND num="+num+" AND subnum="+subnum+" order by idx desc";
		int idx=0;
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(subquery);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				idx=rs.getInt("idx");
			}
		} catch (SQLException e1) {}
		finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
		idx=idx+1;
		String sql="insert into subboard values ('"+code+"',"+num+","+subnum+","+idx+",sysdate,'"+hCode+"','"+id+"','"+content+"')";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("idx"));
			}
			System.out.println(rs);
		} catch (SQLException e) {
			return false;
		} finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
		return true;
	}
	public boolean edit(String code, int num, int subnum, int idx, String content) {
		String sql="update subboard set content='"+content+"' where CODE LIKE '"+code+"' AND NUM="+num+" AND subnum="+subnum+" AND IDX="+idx+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("댓글 edit 실패");
			return false;
		} finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
		
		return true;
	}
	public boolean delete(String code, int num, int subnum, int idx) {
		String sql="delete from subboard where code LIKE '"+code+"' AND NUM="+num+" AND SUBNUM="+subnum+" AND IDX="+idx+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("delete 실패");
			return false;
		}
		return true;
	}
	
}
