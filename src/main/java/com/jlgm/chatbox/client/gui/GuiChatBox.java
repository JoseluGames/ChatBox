package com.jlgm.chatbox.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.jlgm.chatbox.lib.ChatBoxConfigStorage;
import com.jlgm.chatbox.lib.ChatBoxConstants;
import com.jlgm.chatbox.main.ChatBoxMain;
import com.jlgm.chatbox.network.ChatBoxMessage;
import com.jlgm.chatbox.network.ChatBoxPacketHandler;
import com.jlgm.chatbox.tileentity.TileEntityChatBox;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IWorldNameable;

public class GuiChatBox extends GuiScreen{

    private GuiTextField messageTextField;
    private GuiTextField radiusTextField;
	private GuiButton doneButton;
	private static ResourceLocation texture;
	private final TileEntityChatBox tile;
	
	public GuiChatBox(TileEntityChatBox chatBox){
		texture = new ResourceLocation(ChatBoxConstants.MODID + ":" + "textures/gui/test.png");
		tile = chatBox;
	}
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(this.doneButton = new GuiButton(0, this.width/2 - 200/2, (this.height/2 + 166/2) - 28, "Done"));
        this.messageTextField = new GuiTextField(1, this.fontRendererObj, this.width/2 - 248/2 + 8, this.height/2 - 166/2 + 30, 232, 20);
        this.messageTextField.setMaxStringLength(100);
        this.messageTextField.setEnableBackgroundDrawing(true);
        this.messageTextField.setFocused(true);
        this.messageTextField.setCanLoseFocus(true);
        this.messageTextField.setText(tile.getMessage());
        
        this.radiusTextField = new GuiTextField(1, this.fontRendererObj, this.width/2 - 248/2 + 8, this.height/2 - 166/2 + 66, 50, 20);
        this.radiusTextField.setMaxStringLength(100);
        this.radiusTextField.setEnableBackgroundDrawing(true);
        this.radiusTextField.setFocused(false);
        this.radiusTextField.setCanLoseFocus(true);
        this.radiusTextField.setText(String.valueOf(tile.getRadius()));
	}
    
    @Override
    public void updateScreen(){
    	super.updateScreen();
    	if(this.messageTextField.isFocused()) this.messageTextField.updateCursorCounter();
    	if(this.radiusTextField.isFocused()) this.radiusTextField.updateCursorCounter();
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(texture);
		ChatBoxConfigStorage configStorage = ChatBoxMain.instance.configStorage;
		this.drawTexturedModalRect(this.width/2 - 248/2, this.height/2 - 166/2, 0, 0, 248, 166);
		this.fontRendererObj.drawString(new TextComponentString(I18n.format(tile.getName())).getUnformattedText(), this.width/2 - 248/2 + 8, this.height/2 - 166/2 + 8, 0x404040);
		this.fontRendererObj.drawString(new TextComponentString(I18n.format("container.chatbox.message")).getUnformattedText()+ ":", this.width/2 - 248/2 + 8, this.height/2 - 166/2 + 20, 0x404040);
		this.fontRendererObj.drawString(new TextComponentString(I18n.format("container.chatbox.radius")).getUnformattedText() + ":", this.width/2 - 248/2 + 8, this.height/2 - 166/2 + 54, 0x404040);
		this.fontRendererObj.drawString(configStorage.minRadius + " - " + configStorage.maxRadius, this.width/2 - 248/2 + 62, this.height/2 - 166/2 + 72, 0xA0A0A0);
		this.messageTextField.drawTextBox();
		this.radiusTextField.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException{
    	super.keyTyped(typedChar, keyCode);
    	this.messageTextField.textboxKeyTyped(typedChar, keyCode);
    	if(Character.isDigit(typedChar) || Character.isISOControl(typedChar)){
        	this.radiusTextField.textboxKeyTyped(typedChar, keyCode);
    	}
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
    	super.mouseClicked(mouseX, mouseY, mouseButton);
    	this.messageTextField.mouseClicked(mouseX, mouseY, mouseButton);
    	this.radiusTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException{
		if(button == this.doneButton){
			tile.setMessage(this.messageTextField.getText());
			int radius = Integer.valueOf(this.radiusTextField.getText());
			ChatBoxConfigStorage configStorage = ChatBoxMain.instance.configStorage;
			if(radius < configStorage.minRadius){
				radius = configStorage.minRadius;
			}
			if(radius > configStorage.maxRadius){
				radius = configStorage.maxRadius;
			}
			tile.setRadius(radius);
			ChatBoxPacketHandler.INSTANCE.sendToServer(new ChatBoxMessage(this.messageTextField.getText(), Integer.valueOf(this.radiusTextField.getText()), tile.getPos()));
			this.mc.displayGuiScreen(null);
			if(this.mc.currentScreen == null){
				this.mc.setIngameFocus();
			}
		}
	}
}
