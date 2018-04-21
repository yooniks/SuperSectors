package xyz.yooniks.toolssystem.basic.util;

import org.bukkit.entity.Player;
import xyz.yooniks.toolssystem.basic.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserUtil {

    public final static Map<UUID, User> USERS = new HashMap<>();

    public static User getOrCreateUser(Player player) {
        final UUID uuid = player.getUniqueId();

        User user = USERS.get(uuid);

        if (user == null)
            USERS.put(uuid, user = new User(uuid));

        return user;
    }
}
