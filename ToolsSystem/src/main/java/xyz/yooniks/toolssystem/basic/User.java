package xyz.yooniks.toolssystem.basic;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.UUID;

public class User {

    @Setter
    private BukkitRunnable spawnTeleportTask;

    @Getter
    private final UUID uniqueID;

    public User(UUID uuid) {
        this.uniqueID = uuid;
    }

    public Optional<BukkitRunnable> getSpawnTeleportTask() {
        return Optional.ofNullable(spawnTeleportTask);
    }
}
