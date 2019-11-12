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
		//interview 가 3이어야 로그인 가능 id는 lecture에서 강의 시작일기준으로 아이디 생성
		//id는 code+id 를 모두 받아옴 (프런트 부분에서 텍스트 박스로 바로 받아올 것이므로)
		
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
		code=String.copyValueOf(upperArr);//id 입력시 코드부분을 소문자로 입력한경우
		String sql="select * from human where code Like '"+code+"' AND ID LIKE '"+id+"' and PW LIKE '"+pw+"' order by ID";
		System.out.println(sql);
		
		boolean checker=false;
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.next()) {
					System.out.println("로그인 ㄴㄴ");
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
	
	// 로그인시 계정 조회
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
