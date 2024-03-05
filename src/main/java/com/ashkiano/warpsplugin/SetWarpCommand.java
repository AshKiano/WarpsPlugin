package com.ashkiano.warpsplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    private WarpManager warpManager;

    public SetWarpCommand(WarpManager warpManager) {
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /setwarp <name>");
            return true;
        }

        if (!sender.hasPermission("warps.setwarp")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to set warps.");
            return true;
        }

        return warpManager.setWarp((Player) sender, args[0]);
    }
}
