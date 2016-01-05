package at.coro.network.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketHelper {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public SocketHelper(Socket socket) throws IOException {
		this.socket = socket;
		this.ois = new ObjectInputStream(this.socket.getInputStream());
		this.oos = new ObjectOutputStream(this.socket.getOutputStream());
	}

	public void disconnect() throws IOException {
		this.oos.close();
		this.ois.close();
		this.socket.close();
	}

	public Object receiveData(int timeout) throws ClassNotFoundException, IOException {
		this.socket.setSoTimeout(timeout);
		Object o = this.ois.readObject();
		this.socket.setSoTimeout(0);
		return o;
	}

	public Object receiveData() throws ClassNotFoundException, IOException {
		return receiveData(0);
	}

	public void sendData(Object data) throws IOException {
		this.oos.writeObject(data);
	}

}
