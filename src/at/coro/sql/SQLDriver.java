/*    */ package at.coro.sql;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import com.mysql.jdbc.Statement;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class SQLDriver
/*    */ {
/*    */   public static final String version = "0.3";
/*    */   private Statement stmnt;
/*    */   
/*    */   public SQLDriver(String host, String username, String password, String database)
/*    */     throws SQLException
/*    */   {
/* 17 */     Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://" + 
/* 18 */       host + ":3306", username, password);
/* 19 */     this.stmnt = ((Statement)c.createStatement());
/* 20 */     if (!database.isEmpty()) {
/* 21 */       changeDB(database);
/*    */     }
/*    */   }
/*    */   
/*    */   public ResultSet executeQuery(String query) throws SQLException {
/* 26 */     return this.stmnt.executeQuery(query);
/*    */   }
/*    */   
/*    */   public boolean execute(String query) throws SQLException {
/* 30 */     return this.stmnt.execute(query);
/*    */   }
/*    */   
/*    */   public ResultSet autoExecute(String query) throws SQLException {
/* 34 */     if ((query.toUpperCase().startsWith("SELECT")) || 
/* 35 */       (query.toUpperCase().startsWith("SHOW"))) {
/* 36 */       return executeQuery(query);
/*    */     }
/* 38 */     execute(query);
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   public ResultSet getDatabase() throws SQLException
/*    */   {
/* 44 */     return this.stmnt.executeQuery("SELECT DATABASE()");
/*    */   }
/*    */   
/*    */   public ResultSet changeDB(String db) throws SQLException {
/* 48 */     return this.stmnt.executeQuery("USE " + db);
/*    */   }
/*    */   
/*    */   public void disconnect() throws SQLException {
/* 52 */     this.stmnt.close();
/*    */   }
/*    */ }


/* Location:              /home/coro/GitRepo/SQL_Cli.jar!/at/coro/sql/SQLDriver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */