package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditColorGUI;
import me.mancy.alphanations.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditColorGUIHandler implements Listener {

    public EditColorGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleEditColor(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(AdminEditColorGUI.getEditColorInventory()));
        // Get wool color
        // Set nation color to wool color
    }

}
