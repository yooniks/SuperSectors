package xyz.yooniks.toolssystem.util;

import org.bukkit.Location;

public class LocationUtil {

    public static String toString(Location loc) {
        return "x: " + loc.getBlockX() + ", y: " + loc.getBlockY() + ", z: " + loc.getBlockZ();
    }
}
