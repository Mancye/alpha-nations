package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditBlockGUI;
import me.mancy.alphanations.gui.AdminMainGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EditBlockGUIHandler {

    @EventHandler
    private void setBlock(InventoryCloseEvent event) {
        if (!event.getInventory().equals(AdminEditBlockGUI.getEditBlockInventory())) return;
        if (event.getInventory().getItem(4) == null) {

        } else {

        }
    }

}
