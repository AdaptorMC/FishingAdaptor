package net.adaptor.fishing.api;

import net.adaptor.fishing.FishTable;
import net.adaptor.fishing.Main;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

public class FishTableRegistry {

    private static final List<FishTable> tables = new ArrayList<>();
    private static final List<FishTableProvider> providers = new ArrayList<>();


    public static void registerTable(FishTable table) {
        tables.add(table);
    }
    @ApiStatus.AvailableSince("1.0.3")
    public static List<FishTable> getTables() {
        for (FishTableProvider provider : providers) {
            provider.onInitialize();
        }
        return tables;
    }

    // Normal Registry
    @ApiStatus.AvailableSince("1.0.0")
    public static void registerTableProvider(FishTableProvider provider) {
        try {
            providers.add(provider);
            provider.onInitialize();
        } catch (Exception e) {
            System.err.println("Error registering fish tables from provider: " + provider.getClass().getName());
            e.printStackTrace();
        }
    }
    // Print
    @ApiStatus.Experimental
    public static void initializeTables() {
        Main.LOGGER.info("Loading FishTableProviders...");
        int i = 0;
        for (FishTableProvider provider : providers) {
            Main.LOGGER.info(String.format("Found FishTableProvider: %s", provider.getClass().getName()));
            i++;
            Main.LOGGER.info(String.format("Registered fish tables from %s", provider.getClass().getName()));
            Main.LOGGER.info(String.format("Current number of tables registered: %d", tables.size()));
        }
        Main.LOGGER.info(String.format("FishTableProviders loaded. Loaded %d fish tables", i));
        Main.LOGGER.info("Loaded {} Fish tables", tables.size());
    }
}
