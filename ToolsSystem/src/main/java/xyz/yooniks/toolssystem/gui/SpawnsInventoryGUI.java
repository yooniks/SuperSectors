package xyz.yooniks.toolssystem.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import pl.socketbyte.opengui.GUI;
import pl.socketbyte.opengui.GUIExtender;
import pl.socketbyte.opengui.ItemBuilder;
import pl.socketbyte.opengui.Rows;
import pl.socketbyte.opensectors.linker.OpenSectorLinker;
import pl.socketbyte.opensectors.linker.sector.Sector;
import pl.socketbyte.opensectors.linker.sector.SectorManager;
import xyz.yooniks.toolssystem.ToolsSystem;
import xyz.yooniks.toolssystem.task.SpawnTeleportTask;
import xyz.yooniks.toolssystem.util.LocationUtil;

import java.util.Arrays;
import java.util.Map;

public class SpawnsInventoryGUI extends GUIExtender {

    //https://github.com/SocketByte/OpenGUI TODO

    private final Map<Integer, Sector> spawnsBySlots;
    private final ToolsSystem plugin;

    public SpawnsInventoryGUI(ToolsSystem plugin, Map<Integer, Sector> spawns) {
        super(new GUI("&cSpawns", Rows.ONE));
        this.plugin = plugin;

        this.spawnsBySlots = spawns;
        this.buildInventory();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        for (int slot : this.spawnsBySlots.keySet()) {
            if (e.getSlot() != slot) continue;
            final Sector spawnBySlot = this.spawnsBySlots.get(slot);
            if (spawnBySlot == null) return;

            final Player p = (Player) e.getWhoClicked();
            final Sector current = SectorManager.INSTANCE.getSectorMap()
                    .get(OpenSectorLinker.getServerId());
            if (spawnBySlot.getServerController().id == current.getServerController().id) {
                p.sendMessage(ChatColor.RED + "You are already at this spawn!");
                return;
            }
            new SpawnTeleportTask(p, spawnBySlot, plugin);
        }
    }

    private void buildInventory() {
        for (int slot : this.spawnsBySlots.keySet()) {
            final Sector sector = this.spawnsBySlots.get(slot);
            final ItemBuilder builder = new ItemBuilder(Material.WOOL)
                    .setName("&7Sektor: &a" + sector.getServerController().name)
                    .setLore(Arrays.asList("&7Wielkosc sektoru: &6" + sector.getSize(),
                            "&7Koordynaty srodka: &6" + LocationUtil.locationToString(sector.getCenter())));
            this.setItem(slot, builder);
        }
        this.updateInventory();
    }

    @Override
    public void onOpen(InventoryOpenEvent e) {
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
    }
}