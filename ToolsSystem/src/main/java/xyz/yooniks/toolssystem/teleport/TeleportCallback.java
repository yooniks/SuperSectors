package xyz.yooniks.toolssystem.teleport;

public interface TeleportCallback {

    void success();

    void cancel();

    void info(int seconds);

}
