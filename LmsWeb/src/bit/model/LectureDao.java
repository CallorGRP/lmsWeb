package bit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bit.util.db.DataBase;

public class LectureDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//강의현황 리스트를 받아오는 메서드
	public ArrayList<LectureDto> list() {
		
		String sql = "select              		  " +
						    "rownum               " +
						    ", A.*                " +
					"from(                        " +
					    "select                   " +
					        "a.code               " +
					        ", a.lecture_name     " +
					        ", a.content          " +
					        ", a.departday        " +
					        ", a.endday           " +
					        ", a.room             " +
					        ", b.name             " +
					        ", a.regday           " +
					    "from lecture a, human b  " +
					    "where a.code = b.lecture " +
					    "and b.code = 'TE'        " +
					    "order by regday          " +
					") A                          " +
					"order by rownum desc         ";
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		try {
			conn = DataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LectureDto bean = new LectureDto();
				bean.setRownum(rs.getInt("rownum"));
				bean.setCode(rs.getString("code"));
				bean.setLectureName(rs.getString("lecture_name"));
				bean.setDepartday(rs.getDate("departday"));
				bean.setEndday(rs.getDate("endday"));
				bean.setRoom(rs.getString("room"));
				bean.setName(rs.getString("name"));
				bean.setRegday(rs.getDate("regday"));
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
		System.out.println("LE"+"게시판의 list() 메소드 성공");
		return list;
		
	}
	/* 순성 추가 시작*/
	public ArrayList<String> nameList(){
		ArrayList<String> list= new ArrayList<String>();
		
		String sql="select lecture_name, departday, endday from lecture where sysdate<departday order by departday";
		
		try {
			this.conn= DataBase.getConnection();
			this.pstmt= this.conn.prepareStatement(sql);
			this.rs= this.pstmt.executeQuery();
			
			while(rs.next()) {
				String lecName = rs.getString("lecture_name");
				String depDay = rs.getDate("departday").toString();
				String endDay = rs.getDate("endDay").toString();
				String tmp=""+depDay+"~"+endDay+":"+lecName;
				list.add(tmp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(this.rs!=null) {
				try {
					this.rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(this.pstmt!=null) {
				try {
					this.pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(this.conn!=null) {
				try {
					this.conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return list;
	}	
	public String getCode(String departDay, String sub) {
		//select lecture_name, departday, endday from lecture where departday LIKE to_date('2019-08-01') AND Lecture_name LIKE '정보보안 프로그래밍 전문가 양성 과정';
		String sql="select code from lecture where departday LIKE to_date('"+departDay+"') AND Lecture_name LIKE '"+sub+"'";
		String tmp="";
		try {
			conn = DataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			tmp=rs.getString("code");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return tmp;
	}
	/* 순성 추가 끝*/
	//강의를 추가해주는 메서드
	public int lectureAdd(String code, String name, String departday, String endday, int listener, String room, String content){
		int result=0;
		String sql="insert into lecture (code,lecture_name,departday,endday,listener,starttime,endtime,room,content,regday) values (?,?,?,?,?,'50/01/01','50/01/01',?,?,sysdate)";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, name);
			pstmt.setString(3, departday);
			pstmt.setString(4, endday);
			pstmt.setInt(5, listener);
			pstmt.setString(6, room);
			pstmt.setString(7, content);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(result>0){
			System.out.println("Add성공");
		}else{
			System.out.println("실패");
		}
		return result;
		
	}
	
	// 강사에게 담당강의를 배정해주는 메서드
	public int teacherLectureUpdate(String name, String code){
		int result=0;
		String sql="update (select lecture from human where CODE='TE' and NAME=?) set lecture=?";
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
		return result;
	}
	
	//강의 상세내용을 출력해주는 메서드
	public LectureDto detail(String code){
		LectureDto bean=new LectureDto();
		String sql="select * from lecture where code=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()){
					bean.setLectureName(rs.getString("lecture_name"));
					bean.setContent(rs.getString("content"));
					bean.setDepartday(rs.getDate("departday"));
					bean.setEndday(rs.getDate("endday"));
					bean.setRoom(rs.getString("room"));
					bean.setListener(rs.getInt("listener"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	
	//강의에서 담당강사 이름을 뽑아오는 메서드
	public ArrayList<HumanDto> getTeacher(){
		ArrayList<HumanDto> names=new ArrayList<HumanDto>();
		String sql="select name from human where CODE='TE'";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				HumanDto human = new HumanDto();
				human.setName(rs.getString("name"));
				names.add(human);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return names;
	}
	
	//강의 삭제 메서드
	public int lectureDelete(String code){
		int result=0;
		String sql="delete from lecture where code=?";
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		if(result>0){
			System.out.println("삭제성공");
		}else{
			System.out.println("실패");
		}
		
		return result;
	}
	
	//강사의 강의 코드를 null로 만들어주는 메서드
	public int lectureNull(String code) throws SQLException{
		int result=0;
		String sql="update (select * from human where lecture=?) set lecture=''";
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		
		return result;
	}
	
	
	//강의 수정해주는 메서드
	public int lectureEdit(String code, String departday, String endday, String lectureName, int listener, String room, String content){
		int result=0;
		String sql="update (select * from lecture where code=? ) set departday=?, endday=?, lecture_name=?,listener=?,room=?,content=?,regday=sysdate";
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, departday);
			pstmt.setString(3, endday);
			pstmt.setString(4, lectureName);
			pstmt.setInt(5, listener);
			pstmt.setString(6, room);
			pstmt.setString(7, content);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(result>0){
			System.out.println("edit성공");
		}else{
			System.out.println("edit실패");
		}
		
		
		return result;
	}
	
	//강의 신청 학생의 수를 세주는 메서드
	public int joinStudentConunt(String code){
		int result=0;
		String sql="select count(*) as cnt from human where lecture=? and code='ST'";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	//수강 승인된 학생의 수를 세주는 메서드
	public int confirmStudentConunt(String code){
		int result=0;
		String sql="select count(*) as cnt from human where lecture=? and code='ST' and interview=3";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next())result=rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	//강의 리스트를 뽑아주는 메서드
	public ArrayList<LectureDto> getLecture(){
		ArrayList<LectureDto> list=new ArrayList<LectureDto>();
		String sql="select code from lecture";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				LectureDto bean = new LectureDto();
				bean.setCode(rs.getString("code"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	
public ArrayList<LectureDto> nullList() {
		
		String sql = "select              		  " +
						    "rownum               " +
						    ", A.*                " +
					"from(                        " +
					    "select                   " +
					        "a.code               " +
					        ", a.lecture_name     " +
					        ", a.content          " +
					        ", a.departday        " +
					        ", a.endday           " +
					        ", a.room             " +
					        ", b.name             " +
					        ", a.regday           " +
					    "from lecture a, human b  " +
					    "where b.code = 'TE'        " +
					    "order by regday          " +
					") A                          " +
					"order by rownum desc         ";
		ArrayList<LectureDto> list = new ArrayList<LectureDto>();
		try {
			conn = DataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LectureDto bean = new LectureDto();
				bean.setRownum(rs.getInt("rownum"));
				bean.setCode(rs.getString("code"));
				bean.setLectureName(rs.getString("lecture_name"));
				bean.setDepartday(rs.getDate("departday"));
				bean.setEndday(rs.getDate("endday"));
				bean.setRoom(rs.getString("room"));
				bean.setName(rs.getString("name"));
				bean.setRegday(rs.getDate("regday"));
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
