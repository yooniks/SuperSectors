package xyz.yooniks.toolssystem.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bmstefanski.commands.CommandArguments;
import pl.bmstefanski.commands.CommandExecutor;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.GameOnly;
import xyz.yooniks.toolssystem.manager.SpawnManager;

public class SpawnCommand implements CommandExecutor {

    @Command(name = "spawn")
    @GameOnly
    public void execute(CommandSender cs, CommandArguments commandArguments) {
        final Player player = (Player) cs;
        SpawnManager.INSTANCE.getSpawnsGUI().openInventory(player);
    }

    //https://github.com/whippytools/bukkit-commands

}
