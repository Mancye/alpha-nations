package me.mancy.alphanations.listeners;

import me.mancy.alphanations.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NationSelectHandler implements Listener {

    private Main plugin;

    public NationSelectHandler(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onSelect(InventoryClickEvent event) {

    }

}
