package bit.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
   public static Connection getConnection() throws SQLException {
      String Driver="oracle.jdbc.driver.OracleDriver";
      String url="jdbc:oracle:thin:hr/hr@192.168.1.18:1521:xe";
      /*qwerty930704.iptime.org�� ���� �������� DB ������ url/ip�Դϴ� ������Ʈ �۾����� ���� DB���� �����ϵ��� �س����ſ���*/
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
      System.out.println("����DB������ ���Ӽ���");
      return conn;
   }
}