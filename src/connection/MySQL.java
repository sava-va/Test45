package connection;

/**
 *
 * @author savaa
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private static Connection connection;

    public static Connection createConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DBConfig.getUrl(), DBConfig.getUsername(), DBConfig.getPassword()
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet execute(String query) throws SQLException {

        Statement smt = createConnection().createStatement();

        if (query.startsWith("SELECT")) {
            ResultSet resultSet = smt.executeQuery(query);

            return resultSet;
        } else {
            smt.executeUpdate(query);
            return null;
        }

    }

}
