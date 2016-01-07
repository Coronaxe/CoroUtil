package at.coro.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class UDPDiscovery {

	public String[] discover(String keyPhrase, int port) {
		return discover(keyPhrase, port, 10000);
	}

	public String[] discover(String startKeyPhrase, int port, int timeout) {
		DatagramSocket socket = null;
		try {

			socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
			socket.setBroadcast(true);
			socket.setSoTimeout(timeout);

			while (true) {

				byte[] recvBuf = new byte[15000];
				DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
				socket.receive(packet);

				String message = new String(packet.getData()).trim();
				if (message.startsWith(startKeyPhrase)) {
					socket.close();
					return new String[] { packet.getAddress().getHostAddress().toString(),
							new String(packet.getData()).trim() };
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			socket.close();
		}
		return null;
	}

	public void broadcast(String message, int port) {
		// Find the server using UDP broadcast
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();
			// Open a random port to send the package
			socket.setBroadcast(true);

			byte[] sendData = message.getBytes();

			// Try the 255.255.255.255 first
			try {
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						InetAddress.getByName("255.255.255.255"), port);
				socket.send(sendPacket);
				// System.out .println(getClass().getName() +
				// ">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
			} catch (Exception e) {
			}

			// Broadcast the message over all the network interfaces
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

				if (networkInterface.isLoopback() || !networkInterface.isUp()) {
					continue; // Don't want to broadcast to the loopback
								// interface
				}

				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					InetAddress broadcast = interfaceAddress.getBroadcast();
					if (broadcast == null) {
						continue;
					}

					// Send the broadcast package!
					try {
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, port);
						socket.send(sendPacket);
					} catch (Exception e) {
					}

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			socket.close();
		}
	}
}
