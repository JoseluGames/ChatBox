package com.jlgm.chatbox.block;

import com.jlgm.chatbox.lib.ChatBoxConstants;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChatBoxBlock {
	
	public static Block chatBlock_Block;
	public static ItemBlock chatBlock_ItemBlock;
	
	public static void mainRegistry(Configuration configuration){
		initialiseBlock();
	}
	
	public static void initialiseBlock(){
		
		chatBlock_Block = new BlockChatBlock(Material.ROCK).setUnlocalizedName("chatBox").setHardness(2.0F).setResistance(10.0F).setCreativeTab(CreativeTabs.REDSTONE);
		chatBlock_ItemBlock = new ItemBlock(chatBlock_Block);
	}
	
	public static void registerBlock(){
		GameRegistry.register(chatBlock_Block.setRegistryName("chatBox"));
		GameRegistry.register(chatBlock_ItemBlock.setRegistryName(chatBlock_Block.getRegistryName()));
	}
	
	public static void renderBlock(){
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
		renderItem.getItemModelMesher().register(chatBlock_ItemBlock, 0,
				new ModelResourceLocation(ChatBoxConstants.MODID + ":" + "chatBox", "inventory"));
		
	}
}
