package xyz.yooniks.toolssystem.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.UUID;

public class User extends BukkitUser {

    @Setter
    private BukkitRunnable spawnTeleportTask;

    @Getter
    private final UUID uniqueID;
    @Getter
    private final String name;

    public User(Player player) {
        this.setPlayer(player);
        this.uniqueID = player.getUniqueId();
        this.name = player.getName();
    }

    public Optional<BukkitRunnable> getSpawnTeleportTask() {
        return Optional.ofNullable(spawnTeleportTask);
    }
}
