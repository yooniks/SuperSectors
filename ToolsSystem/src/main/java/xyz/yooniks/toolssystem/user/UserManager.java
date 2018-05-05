package xyz.yooniks.toolssystem.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private final Map<UUID, User> users = new HashMap<>();

    public User getUser(Player player) {
        final UUID uuid = player.getUniqueId();

        User user = this.users.get(uuid);

        if (user == null)
            this.users.put(uuid, user = new User(player));

        return user;
    }
}
