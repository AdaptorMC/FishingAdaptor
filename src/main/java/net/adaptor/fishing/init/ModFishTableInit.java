package net.adaptor.fishing.init;

import net.adaptor.fishing.FishTable;
import net.adaptor.fishing.api.FishTableProvider;
import net.adaptor.fishing.api.FishTableRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;

public class ModFishTableInit implements FishTableProvider {
    @Override
    public void addFishTables() {
        FishTable cod = new FishTable(Items.COD).setResultEntities(EntityType.COD);
        FishTable salmon = new FishTable(Items.SALMON).setResultEntities(EntityType.SALMON);
        FishTable pufferfish = new FishTable(Items.PUFFERFISH).setResultEntities(EntityType.PUFFERFISH);
        FishTable tropical_fish = new FishTable(Items.TROPICAL_FISH).setResultEntities(EntityType.TROPICAL_FISH);

        FishTableRegistry.registerTable(cod);
        FishTableRegistry.registerTable(salmon);
        FishTableRegistry.registerTable(pufferfish);
        FishTableRegistry.registerTable(tropical_fish);

    }
}
