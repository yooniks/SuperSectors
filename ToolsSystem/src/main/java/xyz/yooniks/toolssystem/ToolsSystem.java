package xyz.yooniks.toolssystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bmstefanski.commands.BukkitCommands;
import pl.socketbyte.opensectors.linker.OpenSectorLinker;
import pl.socketbyte.opensectors.linker.json.controllers.ServerController;
import pl.socketbyte.opensectors.linker.packet.PacketPlayerInfo;
import pl.socketbyte.opensectors.linker.packet.PacketPlayerTransfer;
import pl.socketbyte.opensectors.linker.sector.Sector;
import pl.socketbyte.opensectors.linker.sector.SectorManager;
import pl.socketbyte.opensectors.linker.util.NetworkManager;
import pl.socketbyte.opensectors.linker.util.PlayerTransferHolder;
import xyz.yooniks.toolssystem.command.SpawnCommand;
import xyz.yooniks.toolssystem.manager.SpawnManager;
import xyz.yooniks.toolssystem.util.ChatUtil;

public final class ToolsSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        final OpenSectorLinker openSectorLinker = OpenSectorLinker.getInstance();
        if (openSectorLinker == null) {
            this.getLogger().warning("\n *** Plugin \"OpenSectorLinker\" is not enabled! *** \n" +
                    "*** This plugin will not work without this plugin! Disabling.. *** \n");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        new SpawnManager(this); //maybe i will add it as a field

        final BukkitCommands bukkitCommands = new BukkitCommands(this);
        bukkitCommands.register(new SpawnCommand());


    }

    @Override
    public void onDisable() {
    }

    public static OpenSectorLinker getOpenSectorLinker() {
        return OpenSectorLinker.getInstance();
    }

    public void sendTransferRequest(Player player, Sector to) {
        int x = player.getLocation().getBlockX(), z = player.getLocation().getBlockZ();
        ServerController current = SectorManager.INSTANCE.getSectorMap()
                .get(OpenSectorLinker.getServerId())
                .getServerController();

        /*PacketSendMessage packetSendMessage = new PacketSendMessage();
        packetSendMessage.setMessage("&7Moving from sector &a"+current.name +" &7to: "+to.getServerController().name);
        packetSendMessage.setPlayerUniqueId(player.getUniqueId().toString());
        packetSendMessage.setMessageType(MessageType.CHAT);*/

        player.sendMessage(ChatUtil.fixColor("&7Moving from sector &a" + current.name + " &7to: " + to.getServerController().name));

        PlayerTransferHolder.getTransfering().add(player.getUniqueId());

        PacketPlayerTransfer packet = new PacketPlayerTransfer();
        packet.setPlayerUniqueId(player.getUniqueId().toString());
        packet.setServerId(to.getServerController().id);

        PacketPlayerInfo packetPlayerInfo = new PacketPlayerInfo(player, x, z);

        packet.setPlayerInfo(packetPlayerInfo);

        NetworkManager.sendTCPSync(packet);

        Bukkit.getServer().getScheduler().runTaskLater(OpenSectorLinker.getInstance(),
                () -> PlayerTransferHolder.getTransfering().remove(player.getUniqueId()), 10);
    }
}
