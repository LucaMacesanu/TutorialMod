//package net.SkipperOnEdge.TutorialMod.item.custom;
//
//import net.SkipperOnEdge.TutorialMod.ModBlocks.ModBlocks;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.context.UseOnContext;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//
//import java.util.Map;
//
//public class ChiselItem extends Item {
//    private static final Map<Block,Block> CHISEL_MAP =
//            Map.of(
//                    Blocks.STONE, Blocks.BRICKS,
//                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
//                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
//                    Blocks.IRON_BLOCK, Blocks.DIAMOND_BLOCK,
//                    Blocks.DIRT, ModBlocks.ALEXANDRITE_BLOCK.get()
//            );
//
//
//    public ChiselItem(Properties pProperties){
//        super(pProperties);
//    }
//
//    @Override
//    public InteractionResult useOn(UseOnContext pContext) {
//        Level level = pContext.getLevel();
//        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();
//
//        if(CHISEL_MAP.containsKey(clickedBlock)){
//            if(!level.isClientSide()){
//                level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
//
//                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
//                        item ->pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
//
//                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
//
//            }
//        }
//
//
//        return InteractionResult.SUCCESS;
//    }
//}
package net.SkipperOnEdge.TutorialMod.item.custom;

import net.SkipperOnEdge.TutorialMod.ModBlocks.ModBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChiselItem extends Item {
    private List<LivingEntity> entity_ids;
    public ChiselItem(Properties pProperties){

        super(pProperties);
        entity_ids = new ArrayList<LivingEntity>();
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();
        for(LivingEntity id : entity_ids){
//            Entity entity = level.getEntity(id.hashCode()); //stuck here
            id.moveTo(pContext.getClickedPos(), 0.0f, 0.0f);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pPlayer.level().isClientSide()) {
            return InteractionResult.PASS;
        }

//        CompoundTag target_tag = pInteractionTarget.getPersistentData();
//        UUID target_uuid = pInteractionTarget.getUUID();
        if(entity_ids.contains(pInteractionTarget)){
            entity_ids.remove(pInteractionTarget);
            System.out.println("Removed from list");
        } else {
            entity_ids.add(pInteractionTarget);
            System.out.println("Added to list");
        }
        return InteractionResult.SUCCESS;
    }
}
