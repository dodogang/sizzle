package net.dodogang.sizzle.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public class FacingBlock extends DirectionalBlock {
    protected FacingBlock(Properties properties) {
        super(properties);

        setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.UP));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return getDefaultState().with(FACING, ctx.getFace());
    }
}
