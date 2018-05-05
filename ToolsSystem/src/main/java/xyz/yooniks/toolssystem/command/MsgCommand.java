package xyz.yooniks.toolssystem.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bmstefanski.commands.CommandArguments;
import pl.bmstefanski.commands.CommandExecutor;
import pl.bmstefanski.commands.annotation.Command;
import pl.bmstefanski.commands.annotation.GameOnly;

public class MsgCommand implements CommandExecutor {

    @Command(name = "spawn", min = 2)
    @GameOnly
    public void execute(CommandSender cs, CommandArguments args) {
        final Player player = (Player) cs;
    }

}
