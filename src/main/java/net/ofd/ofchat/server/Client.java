package net.ofd.chattest.server;

import com.google.gson.Gson;
import net.ofd.chattest.common.packet.*;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


public class Client implements Runnable{

	private Socket socket;//SOCKET INSTANCE VARIABLE
	public String username;
	public String password;

	public Client(Socket s)
	{
		socket = s;//INSTANTIATE THE SOCKET
	}

	@Override
	public void run() //(IMPLEMENTED FROM THE RUNNABLE INTERFACE)
	{
		try //HAVE TO HAVE THIS FOR THE in AND out VARIABLES
		{
			Gson gson = new Gson();
			Scanner in = new Scanner(socket.getInputStream());//GET THE SOCKETS INPUT STREAM (THE STREAM THAT YOU WILL GET WHAT THEY TYPE FROM)
			PrintWriter out = new PrintWriter(socket.getOutputStream());//GET THE SOCKETS OUTPUT STREAM (THE STREAM YOU WILL SEND INFORMATION TO THEM FROM)
			//out.println("test");
			//out.flush();

			while (true)//WHILE THE PROGRAM IS RUNNING
			{
				if (in.hasNext())
				{
					String input = in.nextLine( );//IF THERE IS INPUT THEN MAKE A NEW VARIABLE input AND READ WHAT THEY TYPED
					//System.out.println( input );
					HashMap packet = gson.fromJson( input, HashMap.class );
					//System.out.println(packet.get( "id" ).toString());
					switch ( packet.get("id").toString() )
					{
						case "LoginPacket":
							//System.out.println( "Login" );
							LoginPacket loginPacket = gson.fromJson( input, LoginPacket.class );
							if ( ! verifyLogin( loginPacket.userName, loginPacket.password ) )
							{
								//System.out.println("Failed");
								out.println( gson.toJson( new LoginFailedPacket() ) );
								out.flush( );
								out.close( );
								in.close( );
								socket.close( );
								continue;
							}
							else
							{
								//System.out.println("Success");
								this.username = loginPacket.userName;
								this.password = loginPacket.password;
								//System.out.println( gson.toJson( new LoginSuccessPacket( ) ) );
								out.println(gson.toJson( new LoginSuccessPacket() ));
								out.flush();
							}
							break;
						case "ChatPacket":
							ChatPacket chatPacket = gson.fromJson( input, ChatPacket.class );
							System.out.println(String.format( "%s: <%s> %s", chatPacket.group, this.username, chatPacket.msg ));
							break;
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY THERE WONT BE AN ERROR BUT ITS GOOD TO CATCH
		}
	}

	boolean verifyLogin(String username, String password)
	{
		// TODO Add Login Verification
		System.out.println("Verifying Login");
		return true;
	}

}