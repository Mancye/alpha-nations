package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditColorGUI;
import me.mancy.alphanations.gui.AdminMainGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditColorGUIHandler implements Listener {

    @EventHandler
    private void handleEditColor(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(AdminEditColorGUI.getEditColorInventory())) return;
        // Get wool color
        // Set nation color to wool color
    }

}
