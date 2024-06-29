package net.adaptor.fishing;

import net.adaptor.fishing.api.FishTableRegistry;
import net.adaptor.fishing.event.FishingEvent;
import net.adaptor.fishing.init.ModFishTableInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "fishing_adaptor";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Identifier id(String name) {
		return Identifier.of(Main.MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		FishTableRegistry.registerTableProvider(new ModFishTableInit());
		ServerEntityEvents.ENTITY_LOAD.register(FishingEvent::onFishHookedUp);
		FishTableRegistry.initializeTables();
	}
}