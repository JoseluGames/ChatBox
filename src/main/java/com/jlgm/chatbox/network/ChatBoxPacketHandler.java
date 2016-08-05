package com.jlgm.chatbox.network;

import com.jlgm.chatbox.lib.ChatBoxConstants;
import com.jlgm.chatbox.network.ChatBoxMessage.ChatBoxMessageHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ChatBoxPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ChatBoxConstants.MODID);
	
	public static void registerMessage(){
		INSTANCE.registerMessage(ChatBoxMessageHandler.class, ChatBoxMessage.class, 0, Side.SERVER);
	}
}