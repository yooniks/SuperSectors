package xyz.yooniks.toolssystem;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolsSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        if (!pm.getPlugin("OpenSectorLinker").isEnabled()) {
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
