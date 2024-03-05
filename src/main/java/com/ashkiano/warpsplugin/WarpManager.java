package com.ashkiano.warpsplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WarpManager {
    private WarpsPlugin plugin;

    public WarpManager(WarpsPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean setWarp(Player player, String name) {
        FileConfiguration config = plugin.getConfig();
        Location loc = player.getLocation();
        config.set("warps." + name + ".world", loc.getWorld().getName());
        config.set("warps." + name + ".x", loc.getX());
        config.set("warps." + name + ".y", loc.getY());
        config.set("warps." + name + ".z", loc.getZ());
        config.set("warps." + name + ".yaw", loc.getYaw());
        config.set("warps." + name + ".pitch", loc.getPitch());
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN + "Warp " + name + " has been set!");
        return true;
    }

    public boolean warp(Player player, String name) {
        FileConfiguration config = plugin.getConfig();
        if (!config.contains("warps." + name)) {
            player.sendMessage(ChatColor.RED + "Warp " + name + " does not exist.");
            return false;
        }

        Location loc = new Location(
                plugin.getServer().getWorld(config.getString("warps." + name + ".world")),
                config.getDouble("warps." + name + ".x"),
                config.getDouble("warps." + name + ".y"),
                config.getDouble("warps." + name + ".z"),
                (float) config.getDouble("warps." + name + ".yaw"),
                (float) config.getDouble("warps." + name + ".pitch")
        );

        player.teleport(loc);
        player.sendMessage(ChatColor.GREEN + "Teleported to warp " + name + ".");
        return true;
    }

    public void saveWarps() {
        plugin.saveConfig();
    }

    public boolean deleteWarp(Player player, String name) {
        FileConfiguration config = plugin.getConfig();
        if (!config.contains("warps." + name)) {
            player.sendMessage(ChatColor.RED + "Warp " + name + " does not exist.");
            return false;
        }

        config.set("warps." + name, null); // Remove the warp from the config
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN + "Warp " + name + " has been deleted.");
        return true;
    }
}