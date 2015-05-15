package net.ofd.chattest.client;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Main {

	private final static int PORT = 6677;//SET A CONSTANT VARIABLE PORT
	private final static String HOST = "localhost";//SET A CONSTANT VARIABLE HOST
	protected static String USERNAME = "";
	protected static String PASSWORD = "";

	public static void main(String[] args) throws IOException
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Username: ");
			USERNAME = scanner.nextLine();
			System.out.print("Password: ");
			PASSWORD = scanner.nextLine();
			Socket s = new Socket(HOST, PORT);//CONNECT TO THE SERVER

			System.out.println("You connected to " + HOST);//IF CONNECTED THEN PRINT IT OUT

			Client client = new Client(s);//START NEW CLIENT OBJECT

			Thread t = new Thread(client);//INITIATE NEW THREAD
			t.start();//START THREAD

		}
		catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
}