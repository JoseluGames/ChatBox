package com.jlgm.chatbox.main;

import com.jlgm.chatbox.block.ChatBoxBlock;
import com.jlgm.chatbox.client.gui.ChatBoxGuiHandler;
import com.jlgm.chatbox.lib.ChatBoxConfigStorage;
import com.jlgm.chatbox.tileentity.ChatBoxTileEntity;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent preInitEvent){
		ChatBoxConfigStorage configStorage = ChatBoxMain.instance.configStorage;
		Configuration config = new Configuration(preInitEvent.getSuggestedConfigurationFile());
		config.load();
		configStorage.formatCode = config.getString("Chat box output format", config.CATEGORY_GENERAL, "[%1$s]: %2$s", "Write the desired format putting %1$s where you want the name of the block to be, %2$s for the message and %3$s for the radius, YOU DON'T NEED TO PUT THEM ALL, JUST THE ONES YOU WANT TO USE");
		config.save();

		ChatBoxTileEntity.registerTileEntity();
		ChatBoxBlock.mainRegistry(config);
	}
	
	public void init(FMLInitializationEvent initEven){
		ChatBoxBlock.registerBlock();
		NetworkRegistry.INSTANCE.registerGuiHandler(ChatBoxMain.instance, new ChatBoxGuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent postInitEvent){
		
	}
}