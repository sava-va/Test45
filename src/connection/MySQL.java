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

    private static final String DATABASE = "jdbc_connection";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SAva#123My94";
    private static Connection connection;


    public static Connection createConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/"+ DATABASE,
                        USERNAME,
                        PASSWORD
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
