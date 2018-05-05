package xyz.yooniks.toolssystem.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import xyz.yooniks.toolssystem.util.ChatUtil;

@Getter
@Setter
public class BukkitUser {

    private Player player;

    public void sendMessage(String text) {
        if (this.player == null || !this.player.isOnline()) return;
        this.player.sendMessage(ChatUtil.fixColor(text));
    }
}
