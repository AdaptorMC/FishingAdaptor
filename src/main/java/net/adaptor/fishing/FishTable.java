package net.adaptor.fishing;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import java.util.*;

public class FishTable {
    private final ItemStack tableItem;
    private final Map<EntityType<?>, Integer> resultEntities = new HashMap<>();
    protected ServerWorld world;
    protected PlayerEntity player;
    protected FishingBobberEntity fishingBobber;

    public FishTable(ItemStack itemStack) {
        this.tableItem = itemStack;
    }
    public FishTable(Item item) {
        this.tableItem = item.getDefaultStack();
    }

    public FishTable setResultEntities(EntityType<?>... entityTypes) {
        for (EntityType<?> entityType : entityTypes) {
            resultEntities.merge(entityType, 1, Integer::sum);
        }
        return this;
    }

    public FishTable setResultEntities(String... entityIds) {
        for (String id : entityIds) {
            if (EntityType.get(id).isPresent()) {
                resultEntities.merge(EntityType.get(id).get(), 1, Integer::sum);
            }
        }
        return this;
    }

    public FishTable setUp(ServerWorld world, PlayerEntity player,FishingBobberEntity fishingBobber) {
        this.world = world;
        this.player = player;
        this.fishingBobber = fishingBobber;
        return this;
    }

    public void spawnResultEntities(ItemEntity itemEntity) {
        if (!tableItem.getItem().equals(itemEntity.getStack().getItem())) return;
        for (Map.Entry<EntityType<?>, Integer> set: resultEntities.entrySet()) {
            for (int i=0;i<set.getValue();i++) {
                Entity entity = set.getKey().spawn(world, fishingBobber.getBlockPos(), SpawnReason.EVENT);
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.setVelocity(itemEntity.getVelocity().multiply(1.3,1.6,1.3));
                    livingEntity.setJumping(true);
                    livingEntity.setHeadYaw(player.headYaw);
                    livingEntity.setHealth(2f);
                    livingEntity.setAir(0);
                }
                itemEntity.setDespawnImmediately();
            }
        }
    }
}
