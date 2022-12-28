import java.util.*;
import java.sql.*;

public class LoginDAO {
    public List<LoginVo> getLoginList(){

        List<LoginVo> clients = new ArrayList<LoginVo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;    //데이터 검색

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","Mouth612612*");
            System.out.println("연결 성공");
            rs = conn.createStatement().executeQuery("select * from client");
            pstmt = conn.prepareStatement("select * from client");
            LoginVo client = null;

            while(rs.next()){
                String name = rs.getString("Name");
                String id = rs.getString("ID");
                String pw = rs.getString("Password");
                String gender = rs.getString("Gender");
                client = new LoginVo(name, id, pw, gender);
                clients.add(client);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs, pstmt, conn);
        }
        return clients;
    }
    public int insertLogin(String name1, String id1, String pw1, String gender1){
        int rs = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","Mouth612612*");
            String sql = "insert into client(Name, ID, Password, Gender) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);// ? 4
            pstmt.setString(1,name1);//인덱스, 값(String)
            pstmt.setString(2,id1);
            pstmt.setString(3,pw1);
            pstmt.setString(4,gender1);
            rs = pstmt.executeUpdate();// 반환 :(int) 0 실패, 1 성공 //SQL() 안에 넣지x,
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return rs;
    }
    public int updateLogin(String name1, String id1, String pw1, String gender1){
        int rs = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","Mouth612612*");

            String sql = "update client set Password=?, Name=?, Gender=? where ID=?";
            pstmt = conn.prepareStatement(sql);// ? 4
            pstmt.setString(1,pw1);
            pstmt.setString(2,name1);
            pstmt.setString(3,gender1);
            pstmt.setString(4,id1);
            rs = pstmt.executeUpdate();// 0 실패, 1 성공
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return rs;
    }
    public int deleteLogin(String id1){
        int rs = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","Mouth612612*");

            String sql = "delete from client where ID =?";
            pstmt = conn.prepareStatement(sql);// ? 4
            pstmt.setString(1,id1);
            rs = pstmt.executeUpdate();// 0 실패, 1 성공
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return rs;
    }
    public int yslogin(String id1, String pw1){
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement padmin = null;
        ResultSet rs;
        ResultSet prs;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","Mouth612612*");

            String sql = "select Password from client where ID = ?";
            String admin = "select Gender from client where ID = ?";
            pstmt = conn.prepareStatement(sql);
            padmin = conn.prepareStatement(admin);
            pstmt.setString(1, id1);
            padmin.setString(1, id1);
            rs = pstmt.executeQuery();
            prs = padmin.executeQuery();
            if(rs.next() && prs.next()){
                if(rs.getString(1).equals(pw1)){
                    if(prs.getString(1).equals("X"))
                        return -3;  //관리자
                    else
                        return 1;   //로그인 성공
                }

                else
                    return 0;   //비밀번호 불일치
            }
            return -1;      //아이디가 없음
        } catch(Exception e){
            e.printStackTrace();
        }
        return -2;  //데이터 베이스 오류
    }

}
