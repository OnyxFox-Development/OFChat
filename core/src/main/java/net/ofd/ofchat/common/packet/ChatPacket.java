package net.ofd.ofchat.common.packet;

import com.google.gson.JsonElement;

public class ChatPacket extends Packet
{
	public int group;
	public String msg;

	public ChatPacket ( int gid, String input )
	{
		super( Packets.ChatPacket );
		this.group = gid;
		this.msg = input;
	}
}
