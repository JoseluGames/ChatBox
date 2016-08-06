package com.jlgm.chatbox.event;

import com.jlgm.chatbox.main.ChatBoxMain;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ChatBoxEventHandler {
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onPlayerTickEvent(PlayerTickEvent event){
		if(!ChatBoxMain.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote && !ChatBoxMain.versionChecker.isLatestVersion()){
			ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com");
			Style clickableChatStyle = new Style().setClickEvent(versionCheckChatClickEvent);
			TextComponentString versionWarningChatComponent = new TextComponentString("Your ChatBox is no the latest version! Click here to update");
			versionWarningChatComponent.setStyle(clickableChatStyle);
			ChatBoxMain.haveWarnedVersionOutOfDate = true;
		}
	}
}
