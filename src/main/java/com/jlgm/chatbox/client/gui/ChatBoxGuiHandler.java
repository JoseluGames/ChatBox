package com.jlgm.chatbox.client.gui;

import com.jlgm.chatbox.tileentity.TileEntityChatBox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ChatBoxGuiHandler implements IGuiHandler{
	
	public static final int GUI_ID = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GUI_ID){
			return new GuiChatBox((TileEntityChatBox) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
}
