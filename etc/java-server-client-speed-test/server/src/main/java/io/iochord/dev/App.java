/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.iochord.dev;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    	public static void main(String[] args) throws Exception {
    ServerSocket server = new ServerSocket(6666);
    System.out.println("start server");
		Socket socket = server.accept();
    OutputStream output = socket.getOutputStream();
    
		byte[] bytes = new byte[1000 * 1024]; // 1Mbyte
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = 12;
		}

		while (true) {
			output.write(bytes);
		}
	}
}