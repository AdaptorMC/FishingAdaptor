package net.adaptor.fishing.api;

import net.adaptor.fishing.FishTable;

import java.util.ArrayList;
import java.util.List;

public class FishTableRegistry {
    private static final List<FishTable> tables = new ArrayList<>();

    public static void registerTable(FishTable table) {
        tables.add(table);
    }

    public static void registerTableProvider(FishTableProvider provider) {
        provider.addFishTables();
    }

    public static List<FishTable> getTables() {
        return tables;
    }
}
