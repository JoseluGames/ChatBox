package com.jlgm.chatbox.tileentity;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChatBoxTileEntity {
	
	public static void registerTileEntity(){
		//Item transport
		GameRegistry.registerTileEntity(TileEntityChatBox.class, "chatBox_tileEntity");
	}
}