package jms.master;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Master {
	static ArrayList<String> arrayOfWorkersAddr = new ArrayList<String>();

	public static void main(String args[]) throws IOException {
		// arguments supply message and hostname
		Socket s = null;

		int workerPort = 7899;

		Master m = new Master();
		m.readFileAndStoreInArrayList(); // read file from .txt and store in
											// ArrayList
		File myFile = new File("KKK.txt");
		for (int i = 0; i < arrayOfWorkersAddr.size(); i++) {
			String workerAddr = arrayOfWorkersAddr.get(i);
		//	String messageToWorker = "program = HelloWorld.java";

			try {
				s = new Socket(workerAddr, workerPort);
				byte[] mybytearray = new byte[(int) myFile.length()];
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(myFile));
				bis.read(mybytearray, 0, mybytearray.length);
				OutputStream os = s.getOutputStream();
				os.write(mybytearray, 0, mybytearray.length);
				os.flush();
				System.out.println("Connection Established "+i);

			/*	DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());*/

				System.out.println("Sending data");

				/*out.writeUTF(messageToWorker); // UTF is a string encoding see
												// Sn. 4.4
				String data = in.readUTF(); // read a line of data from the
											// stream
*/				System.out.println("Received: ");

			} catch (UnknownHostException e) {
				System.out.println("Socket:" + e.getMessage());
			} catch (EOFException e) {
				System.out.println("EOF:" + e.getMessage());
			} catch (IOException e) {
				System.out.println("readline:" + e.getMessage());
			} finally {
				if (s != null)
					try {
						s.close();
					} catch (IOException e) {
						System.out.println("close:" + e.getMessage());
					}
			}

		}

	}

	public void readFileAndStoreInArrayList() throws IOException {
		BufferedReader fIn;
		try {
			File inFile = new File("workers_list.txt");
			fIn = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return;
		}
		// store hosts in array list
		String line;
		while ((line = fIn.readLine()) != null) {
			arrayOfWorkersAddr.add(line);
		}
	}

}