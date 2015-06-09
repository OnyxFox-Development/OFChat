package net.ofd.ofchat.common.packet;

public class LoginPacket extends Packet
{
	public String userName;
	public String password;

	public LoginPacket ( String userName, String password )
	{
		super( Packets.LoginPacket );
		this.userName = userName;
		this.password = password;
	}
}