package jms.comms;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	
	public Connection(Socket aClientSocket) {
		clientSocket = aClientSocket;
		//in = new DataInputStream(clientSocket.getInputStream());
		//out = new DataOutputStream(clientSocket.getOutputStream());
		
		
		
		
		this.start();
	}

	public void run() {
		try { // an echo server
			System.out.println("Worker is reading data");
			//String data = in.readUTF(); // read a line of data from the stream
			
			System.out.println("\nDATA RECEIVED:  " /*+ data */+ "\n");
			byte[] mybytearray = new byte[1024];
			//get input stream
			InputStream is = clientSocket.getInputStream();
			//take the input stream file and save on the local drive
			FileOutputStream fos = new FileOutputStream("KKK.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int bytesRead = is.read(mybytearray, 0, mybytearray.length);
			bos.write(mybytearray, 0, bytesRead);
			bos.close();
			System.out.println("worker is writing data");
			
			try {
				
				//write UTF into stream
				InetAddress thisIp = InetAddress.getLocalHost();
				String reply = "IP:" + thisIp.getHostAddress();
				//out.writeUTF(reply);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {/* close failed */
			}
		}
	}
}