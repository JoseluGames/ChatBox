package com.jlgm.chatbox.block;

import com.jlgm.chatbox.lib.ChatBoxConstants;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChatBoxBlock {
	
	public static Block chatBlock_Block;
	public static ItemBlock chatBlock_ItemBlock;

	public static void registerBlocks(RegistryEvent.Register<Block> event){
		chatBlock_Block = new BlockChatBlock(Material.ROCK).setUnlocalizedName("chatbox").setHardness(2.0F).setResistance(10.0F).setCreativeTab(CreativeTabs.REDSTONE);
		event.getRegistry().register(chatBlock_Block.setRegistryName("chatbox"));
	}

	public static void registerItemBlocks(RegistryEvent.Register<Item> event){
		chatBlock_ItemBlock = new ItemBlock(chatBlock_Block);
		event.getRegistry().register(chatBlock_ItemBlock.setRegistryName(chatBlock_Block.getRegistryName()));
	}
	
	public static void renderBlock(){
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
		renderItem.getItemModelMesher().register(chatBlock_ItemBlock, 0,
				new ModelResourceLocation(ChatBoxConstants.MODID + ":" + "chatbox", "inventory"));
		
	}
}
