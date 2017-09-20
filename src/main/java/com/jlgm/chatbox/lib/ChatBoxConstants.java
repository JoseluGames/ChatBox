package com.jlgm.chatbox.lib;

public class ChatBoxConstants {
	//The name "behind the scenes"
	public static final String MODID = "jlgm_chatbox";
	//The public name
	public static final String NAME = "Chat Box";
	
	//Version parts ( https://mcforge.readthedocs.io/en/latest/conventions/versioning/ )
	public static final String MAJOR = "1";
	public static final String MINOR = "3";
	public static final String PATCH = "0";
	public static final String RELEASETYPE = "";
	
	//The version of the mod
	public static final String VERSION = MAJOR + "." + MINOR + "." + PATCH + RELEASETYPE;
	//The Minecraft version this mod is focused to work with
	public static final String ACCEPTEDMINECRAFTVERSIONS = "[1.12.2]";
	
	//The package route of the proxys
	public static final String CLIENT_PROXY = "com.jlgm.chatbox.main.ClientProxy";
	public static final String SERVER_PROXY = "com.jlgm.chatbox.main.ServerProxy";
	
	//The URL to the update file
	public static final String UPDATEJSON = "https://raw.githubusercontent.com/JoseluGames/JoseluGamesUpdateCheckers/master/" + MODID + ".json";
}