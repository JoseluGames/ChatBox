package com.jlgm.chatbox.lib;

public class ChatBoxConstants {
	//The name "behind the scenes"
	public static final String MODID = "jlgm_chatbox";
	//The public name
	public static final String NAME = "Chat Box";
	
	//Version parts ( https://mcforge.readthedocs.io/en/latest/conventions/versioning/ )
	public static final String MCVERSION = "1.12";
	public static final String MAJOR = "0";
	public static final String MINOR = "5";
	public static final String PATCH = "0";
	public static final String RELEASETYPE = "-hotfix";
	
	//The version of the mod
	public static final String VERSION = MCVERSION + "-" + MAJOR + "." + MINOR + "." + PATCH + RELEASETYPE;
	//The Minecraft version this mod is focused to work with
	public static final String ACCEPTEDMINECRAFTVERSIONS = "[1.12]";
	
	//The package route of the proxys
	public static final String CLIENT_PROXY = "com.jlgm.chatbox.main.ClientProxy";
	public static final String SERVER_PROXY = "com.jlgm.chatbox.main.ServerProxy";
}