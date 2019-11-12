package bit.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
   public static Connection getConnection() throws SQLException {
      String Driver="oracle.jdbc.driver.OracleDriver";
      String url="jdbc:oracle:thin:hr/hr@192.168.1.18:1521:xe";
      /*qwerty930704.iptime.org는 집에 만들어놓은 DB 서버의 url/ip입니다 프로젝트 작업간에 같은 DB서버 진입하도록 해놓은거에요*/
      Connection conn=null;
   
      if(conn==null||conn.isClosed()) {
         try {
            Class.forName(Driver);
            conn=DriverManager.getConnection(url);
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      System.out.println("순성DB서버에 접속성공");
      return conn;
   }
}