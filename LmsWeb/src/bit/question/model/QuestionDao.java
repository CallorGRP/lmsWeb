package bit.question.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bit.model.BbsDao;
import bit.util.db.DataBase;

public class QuestionDao extends BbsDao{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boolean addQuestion(String sub,String lecture,int gender,String address,String phone,String email,String content){

		String hcode="";
		String id="";
		try {
		String sql1="select code, id from human where email LIKE '"+email+"'";
		System.out.println(sql1);
			conn=DataBase.getConnection();
			try {
				pstmt=conn.prepareStatement(sql1);
				rs=pstmt.executeQuery();
				rs.next();
				
				hcode=rs.getString("code");
				id=rs.getString("id");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.subClose();
			}
			
			String sql="insert into bbs values ('QE',QEBBS_SEQ.nextVal,0,sysdate,'"+sub+"','"+hcode+"','"+id+"','"+content+"',0,'19000101')";
			System.out.println(sql);
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.subClose();
			this.mainClose();
		}
	      return true;
	}
	public void subClose() {
		
		if(this.rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(this.pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void mainClose() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}