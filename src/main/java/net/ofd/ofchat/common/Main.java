package net.ofd.chattest.common;

import java.io.IOException;
import java.util.Objects;

public class Main
{
	public static void main (String... args) throws IOException
	{
		if ( args.length > 0 && Objects.equals( args[ 0 ], "server" ) )
		{
			System.out.println( "server" );
			net.ofd.chattest.server.Main.main( args );
		}
		else
		{
			net.ofd.chattest.client.Main.main(args);
		}
	}
}
