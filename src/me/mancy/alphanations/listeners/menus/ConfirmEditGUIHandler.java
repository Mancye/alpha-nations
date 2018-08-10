package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.*;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfirmEditGUIHandler implements Listener {

    public ConfirmEditGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onConfirmation(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getName().contains(ChatColor.RED + "Are you sure?"))) return;
        if (NationEditorManager.getPlayersNation((Player)event.getWhoClicked()) == null) return;
        if (!NationEditorManager.playersEditType.containsKey((Player) event.getWhoClicked())) return;
        event.setCancelled(true);
        Nation n = NationEditorManager.getPlayersNation((Player) event.getWhoClicked());
        Player p = (Player) event.getWhoClicked();
        switch (event.getSlot()) {
            case 11:
                if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("BLOCK")) {

                    if (NationEditorManager.blockChanges.containsKey(n)) {
                        n.setItem(NationEditorManager.blockChanges.get(n));
                        NationEditorManager.playersEditType.remove(p);
                        NationEditorManager.blockChanges.remove(n);
                        p.openInventory(AdminMainGUI.getAdminEditGUIPageOne(n));
                    }

                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("NAME")) {

                    if (NationEditorManager.nameChanges.containsKey(n)) {
                        n.setName(NationEditorManager.nameChanges.get(n));
                        NationEditorManager.nameChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminMainGUI.getAdminEditGUIPageOne(n));
                    }

                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("COLOR")) {

                    if (NationEditorManager.colorChanges.containsKey(n)) {
                        n.setColor(NationEditorManager.colorChanges.get(n));
                        NationEditorManager.colorChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminEditColorGUI.getEditColorInventory(n));
                    }

                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("DESCRIPTION_ADD")) {
                    if (NationEditorManager.descriptionChanges.containsKey(n)) {
                        n.getMenuDescription().add(NationEditorManager.descriptionChanges.get(n));
                        NationEditorManager.descriptionChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminEditDescriptionGUI.getDescMenu(n));
                    }
                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("LEADER")) {
                    if (NationEditorManager.leadernameChanges.containsKey(n)) {
                        n.setLeaderName(NationEditorManager.leadernameChanges.get(n));
                        NationEditorManager.leadernameChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminMainGUI.getAdminEditGUIPageTwo(n));
                    }
                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("LEADERSHIP")) {
                    if (NationEditorManager.leadershiptypeChanges.containsKey(n)) {
                        n.setLeadershipType(NationEditorManager.leadershiptypeChanges.get(n));
                        NationEditorManager.leadershiptypeChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminEditLeadershipGUI.getMenu());
                    }
                }else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("CAPITAL")) {
                    if (NationEditorManager.capitalnameChanges.containsKey(n)) {
                        n.setCapitalName(NationEditorManager.capitalnameChanges.get(n));
                        NationEditorManager.capitalnameChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminMainGUI.getAdminEditGUIPageTwo(n));
                    }
                }  else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("DESCRIPTION_REMOVE")) {
                    if (NationEditorManager.descriptionChanges.containsKey(n)) {
                        n.getMenuDescription().remove(NationEditorManager.descriptionChanges.get(n));
                        NationEditorManager.descriptionChanges.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminEditDescriptionGUI.getDescMenu(n));
                    }
                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("DESCRIPTION_CLEAR")) {
                    if (NationEditorManager.descriptionsToClear.contains(n)) {
                        n.resetDescription();
                        NationEditorManager.descriptionsToClear.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(AdminEditDescriptionGUI.getDescMenu(n));
                    }
                } else if (NationEditorManager.playersEditType.get(p).equalsIgnoreCase("DELETE")) {
                    if (NationEditorManager.nationsToDelete.contains(n)) {
                        NationManager.removeNation(n);
                        NationEditorManager.nationsToDelete.remove(n);
                        NationEditorManager.playersEditType.remove(p);
                        p.openInventory(NationSelectionGUI.getAdminNationSelectionInventory());
                    }
                }

                MessageUtil.sendMsgWithPrefix((Player)event.getWhoClicked(), ChatColor.GREEN + "Changes Saved!");
                break;
            case 15:
                event.getWhoClicked().closeInventory();
                MessageUtil.sendMsgWithPrefix((Player)event.getWhoClicked(), ChatColor.RED + "Changes not saved!");
                break;
        }

    }

}
