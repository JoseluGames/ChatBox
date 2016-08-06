package com.jlgm.chatbox.lib;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class ChatBoxVersionChecker implements Runnable{
	
	private static boolean isLatestVersion = false;
	private static String latestVersion = "";
	
	@Override
	public void run() {
		InputStream in = null;
		try{
			in = new URL("https://raw.githubusercontent.com/JoseluGames/ChatBox/mc-1.10.2/versionCheck.txt").openStream();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			latestVersion = IOUtils.readLines(in).get(0);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
		isLatestVersion = ChatBoxConstants.VERSION.equals(latestVersion);
	}
	
	public boolean isLatestVersion(){
		return isLatestVersion;
	}
	
	public String getLatestVersion(){
		return latestVersion;
	}
}
