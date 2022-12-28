import java.util.*;
import java.sql.*;
import java.sql.PreparedStatement;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "root";
        String password = "Mouth612612*";

        try {
            conn = DriverManager.getConnection(url, userName, password);
            Statement statement = conn.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn !=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(PreparedStatement pstmt, Connection conn) {
        try {
            if(pstmt != null) pstmt.close();
            if(conn !=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
