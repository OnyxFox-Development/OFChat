package net.ofd.ofchat.client;
import com.google.gson.Gson;
import net.ofd.ofchat.common.packet.*;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Client implements Runnable {

	private Socket socket;//MAKE SOCKET INSTANCE VARIABLE

	public Client ( Socket s )
	{
		socket = s;//INSTANTIATE THE INSTANCE VARIABLE
	}

	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
		try
		{
			boolean verified = false;
			Gson gson = new Gson();
			int gid = 0;
			Scanner chat = new Scanner(System.in);//GET THE INPUT FROM THE CMD
			Scanner in = new Scanner(socket.getInputStream());//GET THE CLIENTS INPUT STREAM (USED TO READ DATA SENT FROM THE SERVER)
			PrintWriter out = new PrintWriter(socket.getOutputStream());//GET THE CLIENTS OUTPUT STREAM (USED TO SEND DATA TO THE SERVER)

			if (in.hasNext())
			{
				System.out.println(in.nextLine());
			}
			out.println( gson.toJson( new LoginPacket( Main.USERNAME, Main.PASSWORD ) ) );
			out.flush( );

			while (!verified)
			{
				//System.out.println( "Reading" );
				if ( in.hasNext() )
				{
					String packet = in.next( );
					//System.out.println( packet );
					HashMap pckt = gson.fromJson( packet, HashMap.class );
					//System.out.println( pckt.get("id").toString() );
					switch ( pckt.get("id").toString() )
					{
						case "LoginFailedPacket":
							System.out.println("Failed Login!");
							System.exit(1);
							break;
						case "LoginSuccessPacket":
							System.out.println("Login Successful");
							verified = true;
							break;
					}
				}
			}
			while (true)
			{
				if (chat.hasNext())
				{
					String input = chat.nextLine();
					out.println(gson.toJson( new ChatPacket(gid, input) ));
					out.flush();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		}
	}
}


