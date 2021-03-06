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
import xyz.yooniks.toolssystem.teleport.SpawnTeleportTask;
import xyz.yooniks.toolssystem.user.User;
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
        final Sector spawnBySlot = this.spawnsBySlots.get(e.getSlot());
        if (spawnBySlot == null) return;

        final Player player = (Player) e.getWhoClicked();
        final Sector current = SectorManager.INSTANCE.getSectorMap()
                .get(OpenSectorLinker.getServerId());

        if (spawnBySlot.getServerController().id == current.getServerController().id) {
            player.sendMessage(ChatColor.RED + "You are already at this spawn!");
            return;
        }

        final User user = this.plugin.getUserManager().getUser(player);
        if (user.getSpawnTeleportTask().isPresent()) {
            player.sendMessage(ChatColor.RED + "You are already teleporting!");
            return;
        }
        user.setSpawnTeleportTask(new SpawnTeleportTask(user, spawnBySlot, plugin));
    }

    private void buildInventory() {
        for (int slot : this.spawnsBySlots.keySet()) {
            final Sector sector = this.spawnsBySlots.get(slot);
            final ItemBuilder builder = new ItemBuilder(Material.WOOL)
                    .setName("&7Sector's name: &a" + sector.getServerController().name)
                    .setLore(Arrays.asList("&7Size of sector: &6" + sector.getSize(),
                            "&7Coordinates of center: &6" + LocationUtil.toString(sector.getCenter())));
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