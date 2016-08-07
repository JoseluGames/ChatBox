package com.jlgm.chatbox.network;

import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.jlgm.chatbox.tileentity.TileEntityChatBox;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChatBoxMessage implements IMessage{
	
	private String chatMessage;
	private int radius;
	private BlockPos pos;
	private World worldObj;
	
	public ChatBoxMessage(){
		
	}
	
	public ChatBoxMessage(String chatMessage, int radius, BlockPos pos){
		this.chatMessage = chatMessage;
		this.radius = radius;
		this.pos = pos;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(radius);
		buf.writeLong(pos.toLong());
		buf.writeInt(chatMessage.getBytes().length);
		buf.writeBytes(chatMessage.getBytes(Charsets.UTF_8));
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		radius = buf.readInt();
		pos = BlockPos.fromLong(buf.readLong());
		chatMessage = buf.readBytes(buf.readInt()).toString(Charsets.UTF_8);
	}
	
	public static class ChatBoxMessageHandler implements IMessageHandler<ChatBoxMessage, IMessage>{
		
		@Override
		public IMessage onMessage(ChatBoxMessage message, MessageContext ctx) {
			TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.pos);
			if(tile instanceof TileEntityChatBox){
				TileEntityChatBox chatBox = (TileEntityChatBox) tile;
				chatBox.setMessage(message.chatMessage);
				chatBox.setRadius(message.radius);
			}
			return null;
		}
	}
}