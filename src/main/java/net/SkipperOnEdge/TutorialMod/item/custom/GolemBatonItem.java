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

public class GolemBatonItem extends Item {
    private List<UUID> entity_ids;
    public GolemBatonItem(Properties pProperties){

        super(pProperties);
        entity_ids = new ArrayList<UUID>();
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();
        for(UUID id : entity_ids){
            Entity entity = level.getEntity(id.hashCode()); //stuck here
            entity.moveTo(pContext.getClickedPos(), 0.0f, 0.0f);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pPlayer.level().isClientSide()) {
            return InteractionResult.PASS;
        }

//        CompoundTag target_tag = pInteractionTarget.getPersistentData();
        UUID target_uuid = pInteractionTarget.getUUID();
        if(entity_ids.contains(target_uuid)){
            entity_ids.remove(target_uuid);
        } else {
            entity_ids.add(target_uuid);
        }
        return InteractionResult.SUCCESS;
    }
}
