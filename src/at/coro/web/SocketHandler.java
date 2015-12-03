/*    */ package at.coro.web;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.InetAddress;
/*    */ import java.net.MulticastSocket;
/*    */ 
/*    */ public class SocketHandler
/*    */ {
/*    */   public void broadcastInfo(String broadCastAddress, int port, String message) throws IOException
/*    */   {
/* 12 */     byte[] buffer = new byte['Ā'];
/* 13 */     MulticastSocket mcs = new MulticastSocket(port);
/* 14 */     buffer = message.getBytes();
/* 15 */     InetAddress addGroup = InetAddress.getByName(broadCastAddress);
/* 16 */     DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addGroup, port);
/* 17 */     mcs.send(packet);
/* 18 */     mcs.close();
/*    */   }
/*    */ }


/* Location:              /home/coro/GitRepo/SQL_Cli.jar!/at/coro/web/SocketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */