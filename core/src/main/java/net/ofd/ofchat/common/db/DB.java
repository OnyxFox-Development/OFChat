package net.ofd.ofchat.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB
{

	public Connection conn = null;
	public Statement stmt = null;

	public DB()
	{
		try
		{
			Class.forName( "org.sqlite.JDBC" );
			conn = DriverManager.getConnection( "jdbc:sqlite:data.db" );
			stmt = conn.createStatement();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace( );
		}
		catch ( SQLException e )
		{
			e.printStackTrace( );
		}
	}

	public void init() throws SQLException
	{
		stmt.execute( "CREATE TABLE users (userName TEXT PRIMARY KEY, )" );
	}
}
