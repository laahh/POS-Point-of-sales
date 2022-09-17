package utils;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


public class Database {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/rp_fix";
    private static Connection CONNECTION;

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed! Error: " + e);
        }
        return CONNECTION;
    }

    public static ResultSet queryResultSet(String query) throws SQLException { //
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnection().close();
        }
        return null;
    }

    public static void queryExecute(String query) throws SQLException {
        try {
            PreparedStatement pst = getConnection().prepareStatement(query);
        pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getConnection().close();
        }
    }

    public static int queryHitungPendapatanPerBulan(String bulan) {
        int result = 0;
        try {
            String query = "SELECT SUM(total_harga) t_harga FROM transaksi WHERE MONTH(tanggal)='" + bulan + "' ";
            ResultSet res = queryResultSet(query);
            if (res.next()) {
                result = res.getInt("t_harga");
                if (res.wasNull()) {
                    result = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
