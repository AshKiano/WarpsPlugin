package com.ashkiano.warpsplugin;

import org.bukkit.plugin.java.JavaPlugin;

//TODO udělat našeptávač názvů warpů
public class WarpsPlugin extends JavaPlugin {
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        this.warpManager = new WarpManager(this);
        this.getCommand("warp").setExecutor(new WarpCommand(this.warpManager));
        this.getCommand("setwarp").setExecutor(new SetWarpCommand(this.warpManager));
        this.getCommand("delwarp").setExecutor(new DelWarpCommand(this.warpManager)); // Add this line
        saveDefaultConfig();

        Metrics metrics = new Metrics(this, 21186);
    }

    @Override
    public void onDisable() {
        warpManager.saveWarps();
    }
}
