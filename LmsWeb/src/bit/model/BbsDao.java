package bit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bit.util.db.DataBase;;
public class BbsDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public ArrayList<BbsDto> list(String menuCode){
		/*
		 * 메뉴의 코드번호별로 list를 받을 수 있게.
		 * 그냥 부모 list로 선언 해서 bbs에있는 인스턴스를 모두 받으면 한 게시판에 쏴줄 내용 이외에도 다른 메뉴 게시판 내용도 가져와서 안쓰는
		 * 비효율적인 상황이 연출 되므로 처음부터 부모 클래스에서 메소드 정의시 메뉴 코드에 따라서 받을 수 있도록 하였음.
		 * 참고로 list는 글 번호/ 글제목 / 글쓴이/ 글쓴 시간/ 조회수 / 정도만 나오도록 했음. 서버에서 처리시 최소한의 필요한 데이터만 쓰도록
		 * 나름의 최적화랄까.
		 * 글내용이 보이도록 하는건 detail임. 여기서는 content까지 보임.
		 */
		String sql="select * from bbs where code='"+menuCode+"' and subnum=0 order by num desc";//쿼리 수정 요망
		ArrayList<BbsDto> list = new ArrayList<BbsDto>();
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BbsDto bean = new BbsDto();
				bean.setCode(rs.getString("code"));//menu 번호
				bean.setNum(rs.getInt("num"));
				bean.setSubnum(rs.getInt("subnum"));
				bean.setTime(rs.getDate("time"));
				bean.setTimeDetail(rs.getTime("time"));
				bean.setSub(rs.getString("sub"));
				/*
				 * hcode+id 로 글쓴이를 식별 가능하고 식별후에 쿼리를 사용해서 글쓴이의 이름을 알 수 있음.
				 */
				bean.setHcode(rs.getString("hcode"));
				bean.setId(rs.getString("id"));
				//bean.setContent(rs.getString("content"));
				bean.setCnt(rs.getInt("cnt"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("bbs.list() 실패");
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
		//System.out.println(menuCode+"게시판의 list() 메소드 성공");
		return list;
	}
	public BbsDto detail(String menuCode,int num,int subNum){
		BbsDto bean = new BbsDto();
		try {
			String sql2="update bbs set cnt=(select (select cnt from bbs where code='"+menuCode+"' AND num="+num+" AND subnum="+subNum+")+1 from dual) where code='"+menuCode+"' AND num="+num+" AND subnum="+subNum+"";
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql2);
			pstmt.execute();
			if(rs!=null) {
				try {rs.close();} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			conn=DataBase.getConnection();
			String sql="select * from bbs where code='"+menuCode+"' AND num="+num+" AND subNum="+subNum+"";
			System.out.println(sql);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				bean.setCode(rs.getString("code"));
				bean.setNum(rs.getInt("num"));
				bean.setSubnum(rs.getInt("subnum"));
				bean.setTime(rs.getDate("time"));
				bean.setTimeDetail(rs.getTime("time"));
				bean.setSub(rs.getString("sub"));
				bean.setHcode(rs.getString("hcode"));
				bean.setId(rs.getString("id"));
				bean.setContent(rs.getString("content"));
				bean.setCnt(rs.getInt("cnt"));
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
		return bean;
	}
	public boolean add(String menuCode, int subNum, String sub, String hCode, String hId, String content) {
		String sql="insert into bbs (code,num,subnum,time,sub,hcode,id,content,cnt) values"
				+ " ('"+menuCode+"',bbs"+menuCode+"_seq.nextVal,?,sysdate,?,?,?,?,0)";//쿼리 수정 요망
			try {
				conn=DataBase.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, subNum);
				pstmt.setString(2, sub);
				pstmt.setString(3, hCode);
				pstmt.setString(4, hId);
				pstmt.setString(5, content);
				
				rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				if (rs!=null) {try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}}
				if (pstmt!=null) {try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}}
				if (conn!=null) {try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}}
			}
			return true;
	}
	public boolean edit(String code,int num,int subNum, String sub, String content) {
		//작성시간만 따로 sysdate 로 업데이트 되도록 하면 될듯. [where에 코드 들어가고 제목 / 글내용 만 수정]
		String sql="update bbs set sub='"+sub+"', content='"+content+"',time=sysdate where code='"+code+"' AND num="+num+" AND subnum="+subNum+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BbsDao.method[boolean edit()] Error 발생");
			return false;
		} finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pstmt!=null) {try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		return true;
	}
	public boolean delete(String code, int num, int subnum) {
		String sql="delete from bbs where code='"+code+"' AND num="+num+" AND subnum="+subnum+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(pstmt!=null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return true;
	}
	public String getWriterName(String hCode, String hId) {
		//글쓴이 이름 가져오는 메소드
		String answer="";
		String sql="select name from human where code='"+hCode+"' AND id='"+hId+"'";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			answer=""+rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		return answer;
	}
	/*추가*/
	public boolean subNumEdit(String code,int num,int subNum) {
		//작성시간만 따로 sysdate 로 업데이트 되도록 하면 될듯. [where에 코드 들어가고 제목 / 글내용 만 수정]
		String sql="update bbs set subnum=99,time=sysdate where code='"+code+"' AND num="+num+" AND subnum="+subNum+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BbsDao.method[boolean edit()] Error 발생");
			return false;
		} finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(pstmt!=null) {try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		}
		return true;
	}
}
