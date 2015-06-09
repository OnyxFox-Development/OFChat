package net.ofd.ofchat.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main {

	public static HashMap<String, Client> clients = new HashMap<>(  );

	public static void main(String[] args) throws IOException {
		//TODO Add Database Connections
		try
		{
			final int PORT = 6677;//SET NEW CONSTANT VARIABLE: PORT
			ServerSocket server = new ServerSocket(PORT); //SET PORT NUMBER
			System.out.println("Waiting for clients...");//AT THE START PRINT THIS

			while (true)//WHILE THE PROGRAM IS RUNNING
			{
				Socket s = server.accept();//ACCEPT SOCKETS(CLIENTS) TRYING TO CONNECT

				System.out.println("Client connected from " + s.getLocalAddress().getHostName());	//	TELL THEM THAT THE CLIENT CONNECTED

				Client chat = new Client(s);//CREATE A NEW CLIENT OBJECT
				Thread t = new Thread(chat);//MAKE A NEW THREAD
				t.start();//START THE THREAD
			}
		}
		catch (Exception e)
		{
			System.out.println("An error occurred.");//IF AN ERROR OCCURRED THEN PRINT IT
			e.printStackTrace();
		}
	}

}

