package Jdbc;

import java.sql.*;


public final class JdbcUtils {
    /**
     * 数据量url
     */
    private static String url = "jdbc:mysql://localhost:3306/tdh?useSSL=false&characterEncoding=utf-8";
    /**
     * 数据库用户名
     */
    private static String user = "root";
    /**
     * 数据库密码
     */
    private static String password = "root";

    /**
     * 获取数据库连接
     * @return Connection 数据库连接
     * @throws SQLException 异常信息
     * @throws ClassNotFoundException 异常信息
     */
    public static Connection getConnection() throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        //System.out.println("（数据库连接...）");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭数据库资源
     * @param rs ResultSet
     * @param st Statement
     * @param conn Connection
     */
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                        //System.out.println("（连接关闭）");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

