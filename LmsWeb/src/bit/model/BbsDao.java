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
		 * �޴��� �ڵ��ȣ���� list�� ���� �� �ְ�.
		 * �׳� �θ� list�� ���� �ؼ� bbs���ִ� �ν��Ͻ��� ��� ������ �� �Խ��ǿ� ���� ���� �̿ܿ��� �ٸ� �޴� �Խ��� ���뵵 �����ͼ� �Ⱦ���
		 * ��ȿ������ ��Ȳ�� ���� �ǹǷ� ó������ �θ� Ŭ�������� �޼ҵ� ���ǽ� �޴� �ڵ忡 ���� ���� �� �ֵ��� �Ͽ���.
		 * ����� list�� �� ��ȣ/ ������ / �۾���/ �۾� �ð�/ ��ȸ�� / ������ �������� ����. �������� ó���� �ּ����� �ʿ��� �����͸� ������
		 * ������ ����ȭ����.
		 * �۳����� ���̵��� �ϴ°� detail��. ���⼭�� content���� ����.
		 */
		String sql="select * from bbs where code='"+menuCode+"' and subnum=0 order by num desc";//���� ���� ���
		ArrayList<BbsDto> list = new ArrayList<BbsDto>();
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BbsDto bean = new BbsDto();
				bean.setCode(rs.getString("code"));//menu ��ȣ
				bean.setNum(rs.getInt("num"));
				bean.setSubnum(rs.getInt("subnum"));
				bean.setTime(rs.getDate("time"));
				bean.setTimeDetail(rs.getTime("time"));
				bean.setSub(rs.getString("sub"));
				/*
				 * hcode+id �� �۾��̸� �ĺ� �����ϰ� �ĺ��Ŀ� ������ ����ؼ� �۾����� �̸��� �� �� ����.
				 */
				bean.setHcode(rs.getString("hcode"));
				bean.setId(rs.getString("id"));
				//bean.setContent(rs.getString("content"));
				bean.setCnt(rs.getInt("cnt"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("bbs.list() ����");
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
		//System.out.println(menuCode+"�Խ����� list() �޼ҵ� ����");
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
				+ " ('"+menuCode+"',bbs"+menuCode+"_seq.nextVal,?,sysdate,?,?,?,?,0)";//���� ���� ���
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
		//�ۼ��ð��� ���� sysdate �� ������Ʈ �ǵ��� �ϸ� �ɵ�. [where�� �ڵ� ���� ���� / �۳��� �� ����]
		String sql="update bbs set sub='"+sub+"', content='"+content+"',time=sysdate where code='"+code+"' AND num="+num+" AND subnum="+subNum+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BbsDao.method[boolean edit()] Error �߻�");
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
		//�۾��� �̸� �������� �޼ҵ�
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
	/*�߰�*/
	public boolean subNumEdit(String code,int num,int subNum) {
		//�ۼ��ð��� ���� sysdate �� ������Ʈ �ǵ��� �ϸ� �ɵ�. [where�� �ڵ� ���� ���� / �۳��� �� ����]
		String sql="update bbs set subnum=99,time=sysdate where code='"+code+"' AND num="+num+" AND subnum="+subNum+"";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BbsDao.method[boolean edit()] Error �߻�");
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
