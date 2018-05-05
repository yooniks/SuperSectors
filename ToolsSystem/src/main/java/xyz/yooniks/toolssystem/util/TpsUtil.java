package xyz.yooniks.toolssystem.util;

//import net.minecraft.server.v1_8_R3.MinecraftServer;

import org.bukkit.ChatColor;

public class TpsUtil {

    private static double[] getLastTps() {
        return new double[]{20.0};//MinecraftServer.getServer().recentTps;
    }

    private static String getCurrentTps() {
        return format(getLastTps()[0]);
    }

    private static String format(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 20.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }
}
