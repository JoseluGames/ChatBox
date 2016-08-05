package com.jlgm.chatbox.main;

import com.jlgm.chatbox.block.ChatBoxBlock;
import com.jlgm.chatbox.client.gui.ChatBoxGuiHandler;
import com.jlgm.chatbox.lib.ChatBoxConfigStorage;
import com.jlgm.chatbox.network.ChatBoxPacketHandler;
import com.jlgm.chatbox.tileentity.ChatBoxTileEntity;

import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent preInitEvent){
		ChatBoxConfigStorage configStorage = ChatBoxMain.instance.configStorage;
		Configuration config = new Configuration(preInitEvent.getSuggestedConfigurationFile());
		config.load();
		configStorage.formatCode = config.getString("Chat box output format", config.CATEGORY_GENERAL, "[%1$s]: %2$s", "Write the desired format putting %1$s where you want the name of the block to be, %2$s for the message and %3$s for the radius, YOU DON'T NEED TO PUT THEM ALL, JUST THE ONES YOU WANT TO USE");
		configStorage.minRadius = config.getInt("Chat box minimum radius", config.CATEGORY_GENERAL, 1, 1, 1000, "");
		configStorage.maxRadius = config.getInt("Chat box maximum radius", config.CATEGORY_GENERAL, 10, 1, 1000, "");
		config.save();
		
		ChatBoxPacketHandler.registerMessage();
		ChatBoxTileEntity.registerTileEntity();
		ChatBoxBlock.mainRegistry(config);
	}
	
	public void init(FMLInitializationEvent initEven){
		ChatBoxBlock.registerBlock();
		NetworkRegistry.INSTANCE.registerGuiHandler(ChatBoxMain.instance, new ChatBoxGuiHandler());
		GameRegistry.addRecipe(new ShapedOreRecipe(ChatBoxBlock.chatBlock_Block, "CIC", "IRI", "CIC", 'C', "cobblestone", 'R', "dustRedstone", 'I', "ingotIron"));
		//GameRegistry.addShapedRecipe(new ItemStack(ChatBoxBlock.chatBlock_Block));
	}
	
	public void postInit(FMLPostInitializationEvent postInitEvent){
		
	}
}