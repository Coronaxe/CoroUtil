package at.coro.web;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SocketHandler {
	public void broadcastInfo(String broadCastAddress, int port, String message)
			throws IOException {
		byte[] buffer = new byte['Ā'];
		MulticastSocket mcs = new MulticastSocket(port);
		buffer = message.getBytes();
		InetAddress addGroup = InetAddress.getByName(broadCastAddress);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
				addGroup, port);
		mcs.send(packet);
		mcs.close();
	}
}
