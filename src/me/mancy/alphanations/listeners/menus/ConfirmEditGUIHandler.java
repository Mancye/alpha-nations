package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmEditGUIHandler implements Listener {

    public ConfirmEditGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    public void onConfirmation(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Confirm Your Changes"))) return;
        if (NationEditorManager.getPlayersNation((Player)event.getWhoClicked()) == null) return;
        if (!NationEditorManager.playersEditType.containsKey((Player) event.getWhoClicked())) return;

        Nation n = NationEditorManager.getPlayersNation((Player) event.getWhoClicked());

        switch (event.getSlot()) {
            case 11:
                if (NationEditorManager.playersEditType.get((Player) event.getWhoClicked()).equalsIgnoreCase("BLOCK")) {

                    if (NationEditorManager.blockChanges.containsKey(n)) {
                        n.setItem(NationEditorManager.blockChanges.get(n));
                        NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                        NationEditorManager.blockChanges.remove(n);
                    }

                } else if (NationEditorManager.playersEditType.get((Player) event.getWhoClicked()).equalsIgnoreCase("NAME")) {

                    if (NationEditorManager.nameChanges.containsKey(n)) {
                        n.setName(NationEditorManager.nameChanges.get(n));
                        NationEditorManager.nameChanges.remove(n);
                        NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                    }

                } else if (NationEditorManager.playersEditType.get((Player) event.getWhoClicked()).equalsIgnoreCase("COLOR")) {

                    if (NationEditorManager.colorChanges.containsKey(n)) {
                        n.setColor(NationEditorManager.colorChanges.get(n));
                        NationEditorManager.colorChanges.remove(n);
                        NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                    }

                } else if (NationEditorManager.playersEditType.get((Player) event.getWhoClicked()).equalsIgnoreCase("DELETE")) {
                    if (NationEditorManager.nationsToDelete.contains(n)) {
                        NationManager.removeNation(n);
                        NationEditorManager.nationsToDelete.remove(n);
                        NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                    }
                }

            case 15:
                event.getWhoClicked().closeInventory();
        }

    }

}
