package com.jlgm.chatbox.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.jlgm.chatbox.client.gui.ChatBoxGuiHandler;
import com.jlgm.chatbox.main.ChatBoxMain;
import com.jlgm.chatbox.tileentity.TileEntityChatBox;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockChatBlock extends BlockContainer{

	public BlockChatBlock(Material materialIn) {
		super(materialIn);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(worldIn.isRemote){
			playerIn.openGui(ChatBoxMain.instance, ChatBoxGuiHandler.GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
        return true;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        if (!worldIn.isRemote)
        {
        	TileEntity tile = worldIn.getTileEntity(pos);
        	if(tile != null && !tile.isInvalid() && tile instanceof TileEntityChatBox){
        		TileEntityChatBox chatBox = (TileEntityChatBox) tile;
        		boolean flag = worldIn.isBlockPowered(pos);
        		boolean flag1 = chatBox.isPowered();
                
                if (flag && !flag1)
                {
                	chatBox.setPowered(true);
            		chatBox.sendChatMessage();
                    worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
                }
                else if (!flag && flag1)
                {
                	chatBox.setPowered(false);
                }
        	}
        }
    }
	
    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityChatBox)
            {
                ((TileEntityChatBox)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityChatBox();
	}
}
