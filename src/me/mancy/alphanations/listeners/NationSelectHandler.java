package me.mancy.alphanations.listeners;

import me.mancy.alphanations.gui.AdminNationGUI;
import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class NationSelectHandler implements Listener {

    public NationSelectHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onSelect(InventoryClickEvent event) {
        if  (!(event.getInventory().equals(NationSelectionGUI.getPlayerNationSelectionInventory()))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();

        for (Nation nation : NationManager.getNationList()) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                if (event.getClickedInventory().getItem(event.getSlot()).hasItemMeta() && event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) {
                    if (event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains(nation.getName())) {
                        nation.addMember(p);
                        p.sendMessage("You have joined " + nation.getName());
                        p.closeInventory();
                    }
                }
            }
        }

    }

    @EventHandler
    private void onAdminSelect(InventoryClickEvent event) {
        if  (!(event.getInventory().equals(NationSelectionGUI.getAdminNationSelectionInventory()))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();
        Nation selectedNation = null;
        for (Nation nation : NationManager.getNationList()) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                if (event.getClickedInventory().getItem(event.getSlot()).hasItemMeta() && event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) {
                    if (event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains(nation.getName())) {
                        selectedNation = nation;
                    }
                }
            }
        }
        if (selectedNation == null) return;
        AdminNationGUI.getAdminEditGUI(selectedNation);
    }

}
