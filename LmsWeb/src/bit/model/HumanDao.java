package bit.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bit.util.db.DataBase;

public class HumanDao {
   Connection conn;
   PreparedStatement pstmt;
   ResultSet rs;
   int result = 0;
   
   // ����
   public boolean addQuestionStu(String name,String lecture,int gender,String address,String phone,String email){
      String sql="insert into human values ('QE',to_Char(QEID_SEQ.nextVal),' ','"+name+"',null,null,null,'"+lecture+"',sysdate,'19000101',"+gender+",'"+address+"','"+phone+"','"+email+"',9)";
      System.out.println(sql);
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.execute();
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      } finally {
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
      return true;
   }
   
   //����
   
   public boolean addStudent(String name,String lecture,String birth,int gender,String address,String phone,String email){
      try {
         char[] lecArr=lecture.toCharArray();
         for(int i=0;i<lecArr.length;i++) {
            lecArr[i]=Character.toUpperCase(lecArr[i]);
         }
         lecture=String.copyValueOf(lecArr);
         System.out.println(lecture);
         String sql2="select TO_CHAR((select max(departday) from lecture, human where human.CODE Like 'ST' AND lecture.CODE LIKE '"+lecture+"'), 'YYYYMMDD') as identify from dual";
         //lecture�� �´� �ڵ�id�� ������ �ٵ� ���� �ڿ��� �����;��ϴϱ� order by
         System.out.println(sql2);
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql2);
         rs=pstmt.executeQuery();
         String id=null;
         String identify="NONE";
         try {
            rs.next();
            identify = rs.getString("IDENTIFY");
         } catch (Exception e1) {
         }
         
         try {rs.close();} catch (Exception e) {}
         try {pstmt.close();} catch (Exception e) {}
         try {conn.close();} catch (Exception e) {}
         
         String sql3="select max(id) as id from human where id LIKE '"+identify+"___' AND code LIKE 'ST'";
         System.out.println(sql3);
         
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql3);
         rs=pstmt.executeQuery();
         
         rs.next();
         id=rs.getString("id");
         
         try {rs.close();} catch (Exception e) {}
         try {pstmt.close();} catch (Exception e) {}
         try {conn.close();} catch (Exception e) {}
         
         if(id==null) {
            //���񺰷� ù ID�� ����� ���� insert �ϴ� ���� ���� �����ϱ�. ���� ������ ���� if�� ������� ����.
            System.out.println("����� id=null");
            String sql="select departday from lecture where code LIKE '"+lecture+"'";
            System.out.println(sql);
            String dayId=null;
                  
            conn=DataBase.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            if(rs.next()) {
               System.out.println("rs.next()?");
               String departday=rs.getString("departday");
               dayId = departday.substring(0,4)+departday.substring(5,7)+departday.substring(8,10);
            }else {
            }
            
            try {rs.close();} catch (Exception e) {}
            try {pstmt.close();} catch (Exception e) {}
            try {conn.close();} catch (Exception e) {}
            
            dayId=dayId+"001";
            String insert="insert into human values ('ST','"+dayId+"','"+dayId+"','"+name+"','AN',NULL,NULL,'"+lecture+"',sysdate,'"+birth+"',"+gender+",'"+address+"','"+phone+"','"+email+"',0)";
            conn=DataBase.getConnection();
            pstmt=conn.prepareStatement(insert);
            pstmt.execute();
            
            try {rs.close();} catch (Exception e) {}
            try {pstmt.close();} catch (Exception e) {}
            try {conn.close();} catch (Exception e) {}
         }else {
            String front=id.substring(0, 7);
            String back=id.substring(7, 11);
            int idNum=Integer.parseInt(back);
            idNum=idNum+1;
            id=front+idNum;
            String insert="insert into human values ('ST','"+id+"','"+id+"','"+name+"','AN',NULL,NULL,'"+lecture+"',sysdate,'"+birth+"',"+gender+",'"+address+"','"+phone+"','"+email+"',0)";
            System.out.println(insert);
            conn=DataBase.getConnection();
            pstmt=conn.prepareStatement(insert);
            pstmt.execute();
            
            try {rs.close();} catch (Exception e) {}
            try {pstmt.close();} catch (Exception e) {}
            try {conn.close();} catch (Exception e) {}
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(rs!=null){
            try {
               rs.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         if(pstmt!=null){
            try {
               pstmt.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         if(conn!=null){
            try {
               conn.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      return true;
   }
   public String getName(String id) {
      String name="";
      String tmpId=id.substring(0, 2);
      String tmpPw=id.substring(2, 13);
      
      String sql="select name from human where code LIKE '"+tmpId+"' AND id LIKE '"+tmpPw+"'";
      
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            name=rs.getString("name");
         }
      } catch (SQLException e) {
      }
      
      return name;
   }   

      //���⼭���� ��ȣ

   //��û�� �л��� ����Ʈ�� �޴� �޼���
   public ArrayList<HumanDto> getJoinStudentList(int search){
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      String sql="select * from human where code='ST' and interview=? order by lecture";
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, search);
         rs=pstmt.executeQuery();
         while(rs.next()){
            HumanDto bean = new HumanDto();
            bean.setBirth(rs.getDate("birth"));
            bean.setName(rs.getString("name"));
            bean.setId(rs.getString("id"));
            bean.setGender(rs.getInt("gender"));
            bean.setAddress(rs.getString("address"));
            bean.setPhone(rs.getString("phone"));
            bean.setEmail(rs.getString("email"));
            bean.setInterview(rs.getInt("interview"));
            bean.setLecture(rs.getString("lecture"));
            bean.setBranch(rs.getString("branch"));
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
   
   //�Խ��� 12345~ ���������� �ֱ� ���� �л����� ����Ʈ ���� �޾ƿ��� �޼���
   public int getPage(int page) throws SQLException{
      String sql="select count(*) as cnt from human where code='ST' and interview=?";
      int total=0;
      
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, page);
         rs=pstmt.executeQuery();
         if(rs.next())total=rs.getInt("cnt");
      } catch (SQLException e) {
         e.printStackTrace();
      }finally{
         if(rs!=null)rs.close();
         if(pstmt!=null)pstmt.close();
         if(conn!=null)conn.close();
      }
      return total;
   }
   
   //�л��� ���� ��û ���ֱ� ���� �޼���
   public int studentConfirm(String id){
      String sql="update (select * from human where code='ST' and ID=?) set interview=3";
      int result=0;
      
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, id);
         result=pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }   finally{
         try {
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(result>0){
         System.out.println("���� �Ϸ�!");
      }else{
         System.out.println("���� ����");
      }
      return result;
   }
   
   //���� ��û �л����� �������� ������ִ� �޼���
   public HumanDto joinStudentDetail(String id){
      HumanDto bean= new HumanDto();
      String sql="select * from human where id=? and code='ST'";
      
      try {
         conn=DataBase.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, id);
         rs=pstmt.executeQuery();
         if(rs.next()){
            bean.setId(rs.getString("id"));
            bean.setName(rs.getString("name"));
            bean.setLecture(rs.getString("lecture"));
            bean.setRecruit(rs.getDate("recruit"));
            bean.setBirth(rs.getDate("birth"));
            bean.setGender(rs.getInt("gender"));
            bean.setAddress(rs.getString("address"));
            bean.setPhone(rs.getString("phone"));
            bean.setEmail(rs.getString("email"));
            bean.setInterview(rs.getInt("interview"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      
      return bean;
      
   }
   
   //���� ���� �޼���
   public void sendMail(String email){

      String host = "smtp.naver.com";
      String user = "ililil9482";
      String password = "jun708226ty!";
      
      String to = email;
      
      
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");
      
      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
         public PasswordAuthentication getPasswordAuthentication(){
         return new PasswordAuthentication(user,password);
         }
      });
      
      MimeMessage message = new MimeMessage(session);
      try {
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("Bit[�Ⱦ�] ������ �����հݵǼ̽��ϴ�!");
         message.setContent("<h1>�հ��� ���ϵ帳�ϴ�</h1> <button>Bit</button>","text/html; charset=utf-8");
         
         Transport.send(message);
         System.out.println("���� ���� �Ϸ�!");
         
      } catch (MessagingException e) {
         e.printStackTrace();
      }
      
   }
   
   //2019.7.12 �߰�
      //�л��� ������û���ϰ� ����� ��ȸ�ϴ� �޼���
      public int getJoinResult(String name, String phone, String email){
         int result=1;
         String sql="select interview from human where code='ST' and name=? and phone=? and email=?";
         
         try {
            conn=DataBase.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            rs=pstmt.executeQuery();
            if(rs.next())result=rs.getInt("interview");
         } catch (SQLException e) {
            result=1;
            System.out.println("�˻��� ����� �����ϴ�.");
         }
         
         
         return result;
      }
      
    //********* ��ȣ �� ************//
   
   
   
   /********** Ȳ�¿� **************/
   // �����ʻ��� ��ȸ
   public ArrayList<HumanDto> myInfo1List(String id) {
      
      String sql = "SELECT                                             " +
                   "A.CODE                                         " +
                  ",A.ID                                            " +            // ���̵�
                  ",A.NAME                                           " +            // �̸�
                  ",NVL(A.LECTURE,' ') AS LECTURE                      " +            // �����ڵ�
                  ",NVL(FUNC_LNAME('01', A.LECTURE),' ') AS LECTURE_NAME  " +            // ���¸�
                  ",TO_DATE(FUNC_LNAME('02', A.LECTURE)) AS ENDDAY         " +            // ������(������)
                  ",A.GENDER                                       " +            // ����
               "FROM HUMAN A                                         " +
               "WHERE A.CODE = SUBSTR(?,1,2)                           " +
               "AND A.ID = SUBSTR(?,3,11)                              ";
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();

      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setCode(rs.getString("code"));
            bean.setId(rs.getString("id"));
            bean.setName(rs.getString("name"));
            bean.setLecture(rs.getString("lecture"));
            bean.setLectureName(rs.getString("lecture_Name"));
            bean.setEndday(rs.getDate("endday"));
            bean.setGender(rs.getInt("gender"));
//            System.out.println((rs.getString("code")));
//            System.out.println((rs.getString("id")));
//            System.out.println((rs.getString("name")));
//            System.out.println((rs.getString("lecture")));
//            System.out.println((rs.getString("lecture_Name")));
//            System.out.println((rs.getDate("endday")));
//            System.out.println((rs.getInt("gender")));
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
   
   // �����Խ��� ����Ʈ ��ȸ
   public ArrayList<HumanDto> myInfo2List(String id) {

      String sql = "SELECT                                      " +
                   "A.CODE                                " +
                   ",A.LECTURE_NAME                          " +
                   ",C.DUEDATE                                " +
                   ",FUNC_HNAME(B.CODE, B.ID) AS NAME         " +
                   ",C.TIME                                  " +
               "FROM LECTURE A                               " +
                   ", HUMAN B                                " +
                   ", BBS C                                  " +
               "WHERE A.CODE = B.LECTURE                     " +
               "AND B.CODE = C.HCODE                         " +
               "AND B.ID = C.ID                              " +
               "AND A.CODE = (SELECT LECTURE FROM HUMAN      " +
                               "WHERE CODE = SUBSTR(?,1,2)   " +
                               "AND ID = SUBSTR(?,3,11))     " +
               "AND B.CODE = 'TE'                            ";
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setCode(rs.getString("code"));
            bean.setLectureName(rs.getString("lecture_name"));
            bean.setDuedate(rs.getDate("duedate"));
            bean.setName(rs.getString("name"));
            bean.setTime(rs.getDate("time"));
//            System.out.println((rs.getString("code")));
//            System.out.println((rs.getString("lecture_name")));
//            System.out.println((rs.getDate("duedate")));
//            System.out.println((rs.getString("name")));
//            System.out.println((rs.getDate("time")));
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

   // �������� ����Ʈ ��ȸ
   public ArrayList<HumanDto> myInfo3List(String id) {

      String sql = "SELECT                                " +   
                   "ROWNUM                             " +            // �ۼ���
                   ",A.SUB                             " +            // ����
                   ",FUNC_HNAME(A.HCODE, A.ID) AS NAME " +            // �ۼ���
                   ",A.TIME                            " +            // �ۼ�����
               "FROM                                   " +
                  "(                                  " +
                      "SELECT                         " +
                          "SUB                        " +
                          ",CODE                      " +
                          ",HCODE                     " +
                          ",ID                        " +
                          ",TIME                      " +
                      "FROM BBS                       " +
                      "WHERE CODE = 'NO'              " +
                      "ORDER BY TIME                  " +
                  ") A                                " +
               "ORDER BY ROWNUM DESC                   ";
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setRownum(rs.getInt("rownum"));
            bean.setSub(rs.getString("sub"));
            bean.setName(rs.getString("name"));
            bean.setTime(rs.getDate("time"));
//            System.out.println((rs.getInt("rownum")));
//            System.out.println((rs.getString("sub")));
//            System.out.println((rs.getString("name")));
//            System.out.println((rs.getDate("time")));
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

   // ȸ���������� �⺻��ȸ
   public ArrayList<HumanDto> myInfoEditList(String id) {

      String sql = "SELECT                                  " +
                  "CODE                          " +
                   ",ID                                  " +
                  ",PW                          " +
                   ",NAME                                " +
                   ",BIRTH                               " +
                   ",GENDER                         " +
                   ",ADDRESS                             " +
                   ",PHONE                               " +
                   ",EMAIL                               " +
               "FROM HUMAN                               " +
               "WHERE CODE = SUBSTR(?,1,2)              " +
               "AND ID = SUBSTR(?,3,11)                 ";
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setCode(rs.getString("code"));
            bean.setId(rs.getString("id"));
            bean.setPw(rs.getString("pw"));
            bean.setName(rs.getString("name"));
            bean.setBirth(rs.getDate("birth"));
            bean.setGender(rs.getInt("gender"));
            bean.setAddress(rs.getString("address"));
            bean.setPhone(rs.getString("phone"));
            bean.setEmail(rs.getString("email"));
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
   
   // ȸ���������� ����
      public ArrayList<HumanDto> myInfoEditSave(String id, String pw,
            String name, String birth, String gender,
            String address, String phone, String email) {
         
         System.out.println("****************");
         System.out.println(id);
         System.out.println(pw);
         System.out.println(name);
         System.out.println(birth);
         System.out.println(gender);
         System.out.println(address);
         System.out.println(email);
         System.out.println("****************");
         
         ArrayList<HumanDto> list = new ArrayList<HumanDto>();
         
         // ȸ������ ������Ʈ
         String sql = "UPDATE HUMAN SET           " +
                       "NAME = ?               " +
                        ",BIRTH = ?             " +
                        ",GENDER = ?            " +
                        ",ADDRESS = ?           " +
                        ",PHONE = ?             " +
                        ",EMAIL = ?             " +
                      "WHERE CODE = SUBSTR(?,1,2) " +
                    "AND ID = SUBSTR(?,3,11)    " +
                    "AND PW = ?                 ";
         try {
            conn = DataBase.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, birth);
            pstmt.setString(3, gender);
            pstmt.setString(4, address);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.setString(7, id);
            pstmt.setString(8, id);
            pstmt.setString(9, pw);
            System.out.println("rs.next check after");
            result = pstmt.executeUpdate();
            HumanDto bean = new HumanDto();
            bean.setChk(result);
            list.add(bean);
         } catch (SQLException e) {
            e.printStackTrace();
         } catch (Exception e) {
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
   
   // ��й�ȣ���� �⺻��ȸ
   public ArrayList<HumanDto> myInfoPwEditList(String id) {
      
      // ȸ������ ��ȸ
      String sql = "SELECT                                  " +
                  "CODE                          " +
                   ",ID                                  " +
                  ",PW                          " +
               "FROM HUMAN                               " +
               "WHERE CODE = SUBSTR(?,1,2)              " +
               "AND ID = SUBSTR(?,3,11)                 ";
      
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setCode(rs.getString("code"));
            bean.setId(rs.getString("id"));
            bean.setPw(rs.getString("pw"));
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
   
   // ��й�ȣ���� ����
   public ArrayList<HumanDto> myInfoPwSave(String id, String pw2,
         String pwre2) {
      
//      System.out.println("****************");
//      System.out.println(id);
//      System.out.println(pw2);
//      System.out.println(pwre2);
//      System.out.println("****************");
      
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      // ��й�ȣ ������Ʈ
      String sql = "UPDATE HUMAN SET            " +
                    "PW = ?               " +
                "WHERE CODE = SUBSTR(?,1,2)   " +
                "AND ID = SUBSTR(?,3,11)      " +
                "AND PW = ?               ";            
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, pwre2);
         pstmt.setString(2, id);
         pstmt.setString(3, id);
         pstmt.setString(4, pw2);
         result = pstmt.executeUpdate();
         HumanDto bean = new HumanDto();
         bean.setChk(result);
         System.out.println(bean.getChk());
         list.add(bean);
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
   
   // Ż�� ��ȸ
   public ArrayList<HumanDto> myInfoJoinList(String id) {
      
      // Ż������ ��ȸ
      String sql = "SELECT                                  " +
                  "CODE                          " +
                   ",ID                                  " +
                  ",PW                          " +
               "FROM HUMAN                               " +
               "WHERE CODE = SUBSTR(?,1,2)              " +
               "AND ID = SUBSTR(?,3,11)                 ";
      
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setCode(rs.getString("code"));
            bean.setId(rs.getString("id"));
            bean.setPw(rs.getString("pw"));
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
   
   // Ż��
   public ArrayList<HumanDto> myInfoJoinOutDelete(String id, String pw) {
//      System.out.println("****************");
//      System.out.println(id);
//      System.out.println(pw2);
//      System.out.println(pwre2);
//      System.out.println("****************");
      
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      // ȸ������ ����
      String sql = "DELETE FROM HUMAN            " +
                  "WHERE CODE = SUBSTR(?,1,2) " +
                  "AND ID = SUBSTR(?,3,11)   " + 
                  "AND PW = ?               ";      
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         pstmt.setString(3, pw);
         result = pstmt.executeUpdate();
         HumanDto bean = new HumanDto();
         bean.setChk(result);
         System.out.println(bean.getChk());
         list.add(bean);
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
   
   // ������ ���
   public ArrayList<HumanDto> myInfoPrtCompleteList(String id) {
      
//      System.out.println("****************");
//      System.out.println(id);
//      System.out.println(pw2);
//      System.out.println(pwre2);
//      System.out.println("****************");
      
      ArrayList<HumanDto> list = new ArrayList<HumanDto>();
      
      // ���������� ��ȸ
      String sql = "SELECT                    " +
                   "A.NAME                 " +      // �̸�
                   ",B.LECTURE_NAME        " +      // ������
                   ",A.LECTURE            " +      // ��������� ���..
                   ",B.ENDDAY              " +      // ��������
               "FROM HUMAN A               " +      
                   ",LECTURE B             " +
               "WHERE A.LECTURE = B.CODE   " +
               "AND A.CODE = SUBSTR(?,1,2)   " + 
               "AND A.ID = SUBSTR(?,3,11)   ";
      
      try {
         conn = DataBase.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            HumanDto bean = new HumanDto();
            bean.setName(rs.getString("name"));
            bean.setLectureName(rs.getString("lecture_name"));
            bean.setLecture(rs.getString("lecture"));
            bean.setEndday(rs.getDate("endday"));
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
   /*���� �߰�*/

	public String getEmail(int num) {
		String sql="select id from bbs where code LIKE 'QE' AND num="+num+" AND subnum =0";
		String email="";
		try {
			conn=DataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			String id=rs.getString("id");
			
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			
			String sql2="select email from human where code LIKE 'QE' AND id LIKE '"+id+"'";
			
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			
			rs.next();
			
			email=rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
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
		return email;
	}
}


