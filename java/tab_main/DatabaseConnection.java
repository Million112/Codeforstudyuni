package tab_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho;encrypt=true;trustServerCertificate=true";
        String user = "sa"; // Thay bằng tài khoản của bạn
        String password = "123456"; // Thay bằng mật khẩu của bạn
        return DriverManager.getConnection(url, user, password);
    }
}

