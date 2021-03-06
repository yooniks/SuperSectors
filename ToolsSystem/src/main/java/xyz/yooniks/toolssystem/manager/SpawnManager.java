package xyz.yooniks.toolssystem.manager;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import pl.socketbyte.opensectors.linker.sector.Sector;
import pl.socketbyte.opensectors.linker.sector.SectorManager;
import xyz.yooniks.toolssystem.ToolsSystem;
import xyz.yooniks.toolssystem.gui.SpawnsInventoryGUI;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SpawnManager {

    @Getter
    private static SpawnManager instance;

    @Getter
    private final List<Sector> spawns = new LinkedList<>();
    @Getter
    private final SpawnsInventoryGUI spawnsGUI;

    public SpawnManager(ToolsSystem plugin) {
        instance = this;
        final FileConfiguration cf = plugin.getConfig();
        final List<String> sectorsByID = cf.getStringList("spawn-sectors-by-id");

        final Map<Integer, Sector> spawns = new LinkedHashMap<>();
        final SectorManager sectorManager = SectorManager.INSTANCE;
        for (Integer id : sectorManager.getSectorMap().keySet()) {
            final Sector sector = sectorManager.getSectorMap().get(id);
            if (sectorsByID.contains(id.toString())) {
                spawns.put(id, sector);
            }
        }

        this.spawnsGUI = new SpawnsInventoryGUI(plugin, spawns);

    }

}
