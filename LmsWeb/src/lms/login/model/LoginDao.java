package lms.login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bit.model.HumanDao;
import bit.util.db.DataBase;

/**
 * 
 */
public class LoginDao extends HumanDao{
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
	public boolean login(LoginDto loginData){
		//interview �� 3�̾�� �α��� ���� id�� lecture���� ���� �����ϱ������� ���̵� ����
		//id�� code+id �� ��� �޾ƿ� (����Ʈ �κп��� �ؽ�Ʈ �ڽ��� �ٷ� �޾ƿ� ���̹Ƿ�)
		
		String code="wrong";
		String id="wrong";
		String pw="wrong";
		try {
			code = loginData.getLoginId().substring(0,2);
			id = loginData.getLoginId().substring(2,13);
			pw = loginData.getLoginPw();
			
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		System.out.println(code+id+pw);
		if(check(code,id,pw)){
			return true;
		}else{
			return false;
		}
	}
	private boolean check(String code, String id, String pw){
		System.out.println(code+id+pw);
		char[] upperArr=code.toCharArray();
		for(int i=0;i<upperArr.length;i++) {
			upperArr[i]=Character.toUpperCase(upperArr[i]);
		}
		System.out.println(code+id+pw);
		code=String.copyValueOf(upperArr);//id �Է½� �ڵ�κ��� �ҹ��ڷ� �Է��Ѱ��
		String sql="select * from human where code Like '"+code+"' AND ID LIKE '"+id+"' and PW LIKE '"+pw+"' order by ID";
		System.out.println(sql);
		
		boolean checker=false;
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.next()) {
					System.out.println("�α��� ����");
				}else {
					checker = true;
				}
			}			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally{
			if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
		return checker;
	}
	
	// �α��ν� ���� ��ȸ
	public ArrayList<LoginDto> daoLoginConn(String id, String pw) {
		
		String sql = "SELECT                                  " +
					    "NVL(CONCAT(CODE,ID),NULL) AS ID      " +
					    ",NAME								  " +
					    ",COUNT(*) AS CHK                     " +
					"FROM HUMAN                               " +
					"WHERE CODE = SUBSTR(?,1,2) 			  " +
					"AND ID = SUBSTR(?,3,11)   				  " +
					"AND PW = ?                               " +
					"GROUP BY CONCAT(CODE,ID),NAME    		  ";
		
		ArrayList<LoginDto> list = new ArrayList<LoginDto>();
		
		try {
			conn = DataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LoginDto bean = new LoginDto();
				bean.setLoginId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setChk(rs.getString("chk"));
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("chk"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
