package me.mancy.alphanations.listeners;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.entity.Player;
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
        if  (!(event.getInventory().equals(NationSelectionGUI.getSelectionInventory()))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

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

}
