package jms.worker;

import java.net.*;
import java.io.*;
import jms.comms.*;

public class Worker {
	public static void main(String args[]) {
		try {
			int serverPort = 7899; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			int i = 0;
			while (true) {
				System.out.println("Worker is listening for a connection");
				Socket clientSocket = listenSocket.accept();
				i++;
				System.out.println("Received connection " + i);
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}