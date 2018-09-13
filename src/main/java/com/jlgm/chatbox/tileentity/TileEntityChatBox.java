package com.jlgm.chatbox.tileentity;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import com.jlgm.chatbox.main.ChatBoxMain;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IWorldNameable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityChatBox extends TileEntity implements IWorldNameable{
	
	private String message;
	private String customName;
	private int radius;
	
	private boolean powered;
	
	public TileEntityChatBox(){
		this.message = "Hello there! - General Kenobi!";
		this.radius = 10;
	}
	
	public boolean sendChatMessage(){
		List list = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.add(-radius, -radius, -radius), this.pos.add(radius + 1, radius + 1, radius + 1)));
		if(!list.isEmpty()){	
			Iterator listItr = list.iterator();
			while(listItr.hasNext()){
				EntityPlayer player = (EntityPlayer)listItr.next();
				String blockName = "Chat Box";
				if(this.customName != null){
					blockName = customName;
				}
				player.sendMessage(new TextComponentString(String.format(ChatBoxMain.instance.configStorage.formatCode, blockName, message, radius)));
			}
			return true;
		}
		return false;
	}
	
    public void setPowered(boolean poweredIn)
    {
        this.powered = poweredIn;
    }

    public boolean isPowered()
    {
        return this.powered;
    }
    
    public String getMessage(){
    	return this.message;
    }
    
    public void setMessage(String par1String){
    	this.message = par1String;
    }
    
    public int getRadius(){
    	return this.radius;
    }
    
    public void setRadius(int par1){
    	this.radius = par1;
    }
    
    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.chatbox";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String name)
    {
        this.customName = name;
    }
	
	@Override
    public NBTTagCompound getUpdateTag(){
		NBTTagCompound tag = super.getUpdateTag();
        return this.writeToNBT(tag);
    }
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setString("Message", this.message);
		tagCompound.setString("CustomName", this.customName);
		tagCompound.setInteger("Radius", this.radius);
		return tagCompound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.message = tagCompound.getString("Message");
		this.customName = tagCompound.getString("CustomName");
		this.radius = tagCompound.getInteger("Radius");
	}
	
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket(){
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), tagCompound);
    }
   
    @Override
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt){
        NBTTagCompound tag = pkt.getNbtCompound();
        readFromNBT(tag);
    }
}
