package xyz.yooniks.toolssystem.task;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.socketbyte.opensectors.linker.sector.Sector;
import xyz.yooniks.toolssystem.ToolsSystem;

public class SpawnTeleportTask extends BukkitRunnable {

    private final Player player;
    private final Sector spawn;

    private final ToolsSystem plugin;

    private final Location startLocation;
    private int time = 10;

    public SpawnTeleportTask(Player player, Sector spawn, ToolsSystem plugin) {
        this.plugin = plugin;

        this.player = player;
        this.spawn = spawn;
        this.startLocation = player.getLocation().clone();

        this.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            this.cancel();
            return;
        }
        if (startLocation.getBlockX() != player.getLocation().getBlockX()
                || startLocation.getBlockZ() != player.getLocation().getBlockZ()) {

            if (player.isOnline())
                player.sendMessage(ChatColor.RED + "You have just moved! Teleportation cancelled!");

            this.cancel();
            return;
        }
        if (time <= 0) {
            player.sendMessage(ChatColor.RED + "Teleporting!");
            plugin.sendTransferRequest(player, spawn);
            return;
        }
        player.sendMessage(ChatColor.RED + "Teleporting in: " + time + " seconds..");
        time--;
    }
}
