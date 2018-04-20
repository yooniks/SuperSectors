package xyz.yooniks.toolssystem;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.socketbyte.opensectors.system.OpenSectorSystem;

public final class ToolsSystem extends JavaPlugin {


    @Override
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        final OpenSectorSystem openSectorSystem = OpenSectorSystem.getInstance();
        if (openSectorSystem==null) {
            this.getLogger().warning("\n *** Plugin \"OpenSectorLinker\" is not enabled! *** \n" +
                    "*** This plugin will not work without this plugin! Disabling.. *** \n");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

    }

    @Override
    public void onDisable() {
    }
}
