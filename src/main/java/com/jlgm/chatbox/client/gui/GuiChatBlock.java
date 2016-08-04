package com.jlgm.chatbox.client.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiChatBlock extends GuiScreen{
	
	private GuiButton doneButton;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui(){
		this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, "Done"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException{
		if(button == this.doneButton){
			this.mc.displayGuiScreen(null);
			if(this.mc.currentScreen == null){
				this.mc.setIngameFocus();
			}
		}
	}
}
