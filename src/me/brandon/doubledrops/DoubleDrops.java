package me.brandon.doubledrops;

import org.bukkit.plugin.java.JavaPlugin;

public class DoubleDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        DoubleCommand dc = new DoubleCommand();
        getCommand("drops").setExecutor(dc);
        getServer().getPluginManager().registerEvents(dc, this);

    }

}

