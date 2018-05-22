package classes;

import java.sql.*;

public final class Socket {
    private static Connection connection;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://" + Settings.getSqlHost() + ":" + Settings.getSqlPort() + "/" + Settings.getSqlDatabase(), Settings.getSqlUser(), Settings.getSqlPassword());
    }

    public static void connect(String host, String port, String database, String user, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager
                .getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ResultSet getAll(String table) {
        PreparedStatement stmt=null;
        try {
            connect();
            stmt = connection.prepareStatement("SELECT * FROM ?");
//            stmt.set(1, table);
            return stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            close();
        }
        return  null;
    }
}
