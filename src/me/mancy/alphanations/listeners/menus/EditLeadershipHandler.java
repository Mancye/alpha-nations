package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditLeadershipHandler implements Listener {
    public EditLeadershipHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleClicks(InventoryClickEvent event) {
        if (!(event.getInventory().getName().contains("Select Leadership Type"))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if ((NationEditorManager.getPlayersNation((Player) event.getWhoClicked()) == null)) return;
        Player p = (Player) event.getWhoClicked();
        Nation nation = NationEditorManager.getPlayersNation(p);
        event.setCancelled(true);
        switch (event.getSlot()) {
            case 10:
                NationEditorManager.playersEditType.put(p, "LEADERSHIP");
                NationEditorManager.leadershiptypeChanges.put(nation, "Monarchy");
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                break;
            case 12:
                NationEditorManager.playersEditType.put(p, "LEADERSHIP");
                NationEditorManager.leadershiptypeChanges.put(nation, "Diplomacy");
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                break;
            case 14:
                NationEditorManager.playersEditType.put(p, "LEADERSHIP");
                NationEditorManager.leadershiptypeChanges.put(nation, "Tyranny");
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                break;
            case 18:
                p.openInventory(AdminMainGUI.getAdminEditGUIPageTwo(nation));
                break;
        }

    }

}
