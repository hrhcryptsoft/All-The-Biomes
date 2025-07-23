package net.haydenji.allthebiomes.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Hammer extends Item {
    public static final float speed=4.0f;
    public Hammer(Properties pProperties){
        super(pProperties);
    }
    private static final Map<Block, Block> HAMMER_MAP =
            Map.of(
                    Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK
            );
    @Override public InteractionResult useOn(UseOnContext pContext){
        Level level = pContext.getLevel();
        Block block = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(HAMMER_MAP.containsKey(block)){
            if (!level.isClientSide()){
                level.setBlockAndUpdate(pContext.getClickedPos(),HAMMER_MAP.get(block).defaultBlockState());
            }
            level.playSound(null, pContext.getClickedPos(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS);
        }
        return super.useOn(pContext);
    }
    @Override public ItemUseAnimation getUseAnimation(ItemStack itemStack){
        return ItemUseAnimation.SPEAR;
    }
    @Override public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity){
        return 72000;
    }
    @Override public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i){
        if(livingEntity instanceof Player player){
            float g = player.getYRot();
            float h = player.getXRot();
            float k = -Mth.sin(g*(float)(Math.PI/180.0))*Mth.cos(h*(float)(Math.PI/180.0));
            float l = -Mth.sin(h*(float)(Math.PI/180.0));
            float m = Mth.cos(g*(float) (Math.PI/180.0))*Mth.cos(h*(float) (Math.PI/180.0));
            float n = Mth.sqrt(k*k+l*l+m*m);

            k=k*speed/n;
            l=l*speed/n;
            m=m*speed/n;

            player.push(k,l,m);
            player.startAutoSpinAttack(3,150.0f,itemStack);

            if (player.onGround()){
                float o = 1.19999f;
                player.move(MoverType.SELF,new Vec3(0.0,o,0.0));
            }
            return true;

        }
        return false;
    }

    @Override public  InteractionResult use(Level level, Player player, InteractionHand interactionHand){
        player.startUsingItem(interactionHand);
        return super.use(level, player, interactionHand);
    }
    @Override public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, @NotNull Player pPlayer){
        return !pPlayer.isCreative();
    }
}
