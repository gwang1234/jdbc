package textboard;

import java.sql.*;

public class JDBCSelect {
  public static void main(String[] args) {

    Connection conn = null;//연결
    PreparedStatement pstat= null;//sql 문장 실행 인터페이스
    ResultSet rs=null;// SQL 쿼리 실행 결과

    try{
      Class.forName("com.mysql.jdbc.Driver");

      String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

      conn = DriverManager.getConnection(url, "root", "5615");

      String sql = "SELECT * FROM article";

      pstat = conn.prepareStatement(sql);

      rs = pstat.executeQuery();

      while (rs.next()) {//next()다음 행이 존재하면 boolean 값으로 반환
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String body = rs.getString("body");
        String regDate = rs.getString("regDate");

        System.out.println("id: " + id + ", title: " + title + ", body: " + body + ", regDate: " + regDate);
      }

    }
    catch(ClassNotFoundException e){
      System.out.println("드라이버 로딩 실패");
    }
    catch(SQLException e){
      System.out.println("에러: " + e);
    }
    finally{
      try{
        if( conn != null && !conn.isClosed()){//초기화 됨&& conn 닫혀있지 않으면
          conn.close();
        }
      }
      catch( SQLException e){
        e.printStackTrace();
      }
      try{
        if(pstat !=null&&!pstat.isClosed()){
          pstat.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try{
        if( rs != null && !rs.isClosed()){
          rs.close();
        }
      }
      catch( SQLException e){
        e.printStackTrace();
      }

    }
  }
}
