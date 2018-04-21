package xyz.yooniks.toolssystem.util;

import org.bukkit.Location;

public class LocationUtil {

    public static String locationToString(Location loc) {
        final StringBuilder builder = new StringBuilder();
        builder.append("x: ");
        builder.append(loc.getBlockX());
        builder.append(" ");
        builder.append("y: ");
        builder.append(loc.getBlockY());
        builder.append(" ");
        builder.append("z: ");
        builder.append(loc.getBlockZ());
        return builder.toString();
    }
}
