package xyz.yooniks.toolssystem.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.socketbyte.opensectors.linker.sector.Sector;
import xyz.yooniks.toolssystem.ToolsSystem;
import xyz.yooniks.toolssystem.user.User;

public class SpawnTeleportTask extends BukkitRunnable {

    private final User user;
    private final Sector spawn;

    private final ToolsSystem plugin;

    private final Location startLocation;
    private int time = 10;

    private final TeleportCallback callback;

    public SpawnTeleportTask(User user, Sector spawn, ToolsSystem plugin) {
        this.plugin = plugin;

        this.user = user;
        this.spawn = spawn;
        this.startLocation = this.user.getPlayer().getLocation();

        this.callback = new TeleportCallback() {
            @Override
            public void success() {
                user.sendMessage("&6Successfully teleported!");
            }

            @Override
            public void cancel() {
                user.sendMessage("&cTeleportation cancelled!");
                SpawnTeleportTask.this.cancel();
                user.setSpawnTeleportTask(null);
            }

            @Override
            public void info(int seconds) {
                user.sendMessage("&cTeleporting in: &6" + seconds + "sec&c..");
            }
        };

        this.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }

    @Override
    public void run() {
        final Player player = this.user.getPlayer();
        if (player == null || !player.isOnline()) {
            this.callback.cancel();
            return;
        }
        if (this.startLocation.getX() != player.getLocation().getX()
                || this.startLocation.getZ() != player.getLocation().getZ()
                || this.startLocation.getY() != player.getLocation().getY()) {
            this.callback.cancel();
            return;
        }
        if (time <= 0) {
            this.plugin.sendTransferRequest(player, spawn);
            this.callback.success();
            return;
        }
        this.callback.info(this.time);
        this.time--;
    }
}
