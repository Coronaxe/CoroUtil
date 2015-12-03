/*    */ package at.coro.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class ConfigManager
/*    */ {
/*    */   private String path;
/*    */   
/*    */   public ConfigManager(String configPath)
/*    */   {
/* 16 */     this.path = configPath;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Properties loadConfig()
/*    */     throws IOException
/*    */   {
/* 28 */     Properties config = new Properties();
/* 29 */     InputStream iStream = new FileInputStream(this.path);
/* 30 */     config.load(iStream);
/* 31 */     return config;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void saveConfig(Properties config)
/*    */     throws IOException
/*    */   {
/* 44 */     java.io.OutputStream oStream = new FileOutputStream(this.path);
/* 45 */     config.store(oStream, null);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean configExists()
/*    */   {
/* 57 */     File propFile = new File(this.path);
/* 58 */     return propFile.exists();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void deleteConfig()
/*    */   {
/* 65 */     File propFile = new File(this.path);
/* 66 */     propFile.delete();
/*    */   }
/*    */ }


/* Location:              /home/coro/GitRepo/SQL_Cli.jar!/at/coro/utils/ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */