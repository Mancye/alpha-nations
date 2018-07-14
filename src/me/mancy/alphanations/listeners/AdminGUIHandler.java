package me.mancy.alphanations.listeners;

import me.mancy.alphanations.gui.AdminNationGUI;
import me.mancy.alphanations.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGUIHandler {

    public AdminGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleMainGUIClicks(InventoryClickEvent event) {
        if (!(event.getClickedInventory().equals(AdminNationGUI.getAdminGUI()))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        switch (event.getSlot()) {

            case 10: {
                p.openInventory(NationSelectionGUI)
                break;
            }
            case 12: {
                break;
            }
            case 14: {
                break;
            }
            case 16: {
                break;
            }
        }

    }

}
