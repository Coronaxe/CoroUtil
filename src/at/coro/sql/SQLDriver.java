package at.coro.sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLDriver {
	
	public final String version = "0.3";
	
	private Statement stmnt;

	public SQLDriver(String host, String username, String password,
			String database) throws SQLException {
		Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://"
				+ host + ":3306", username, password);
		this.stmnt = ((Statement) c.createStatement());
		if (!database.isEmpty()) {
			changeDB(database);
		}
	}

	public ResultSet executeQuery(String query) throws SQLException {
		return this.stmnt.executeQuery(query);
	}

	public boolean execute(String query) throws SQLException {
		return this.stmnt.execute(query);
	}

	public ResultSet autoExecute(String query) throws SQLException {
		if ((query.toUpperCase().startsWith("SELECT"))
				|| (query.toUpperCase().startsWith("SHOW"))) {
			return executeQuery(query);
		}
		execute(query);
		return null;
	}

	public ResultSet getDatabase() throws SQLException {
		return this.stmnt.executeQuery("SELECT DATABASE()");
	}

	public ResultSet changeDB(String db) throws SQLException {
		return this.stmnt.executeQuery("USE " + db);
	}

	public void disconnect() throws SQLException {
		this.stmnt.close();
	}
}