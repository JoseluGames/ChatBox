package com.jlgm.chatbox.main;

import com.jlgm.chatbox.lib.ChatBoxConfigStorage;
import com.jlgm.chatbox.lib.ChatBoxConstants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ChatBoxConstants.MODID,
	name = ChatBoxConstants.NAME,
	version = ChatBoxConstants.VERSION,
	acceptedMinecraftVersions = ChatBoxConstants.ACCEPTEDMINECRAFTVERSIONS,
	updateJSON = ChatBoxConstants.UPDATEJSON)

public class ChatBoxMain{
	
	public static ChatBoxConfigStorage configStorage;
	
	@SidedProxy(clientSide = ChatBoxConstants.CLIENT_PROXY, serverSide = ChatBoxConstants.SERVER_PROXY)
	public static CommonProxy proxy;
	@Mod.Instance(ChatBoxConstants.MODID)
	public static ChatBoxMain instance;
	
	@Mod.EventHandler
	public static void PreInit(FMLPreInitializationEvent preInitEvent){
		configStorage = new ChatBoxConfigStorage();
		proxy.preInit(preInitEvent);
	}
	
	@Mod.EventHandler
	public static void Init(FMLInitializationEvent initEvent){
		proxy.init(initEvent);
	}
	
	@Mod.EventHandler
	public static void PostInit(FMLPostInitializationEvent postInitEvent){
		proxy.postInit(postInitEvent);
	}
}