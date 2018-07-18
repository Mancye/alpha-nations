package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditBlockGUI;
import me.mancy.alphanations.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EditBlockGUIHandler implements Listener {

    public EditBlockGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void setBlock(InventoryCloseEvent event) {
        if (!event.getInventory().equals(AdminEditBlockGUI.getEditBlockInventory())) return;
        if (event.getInventory().getItem(4) == null) {

        } else {

        }
    }

}
