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
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AdminMainGUIHandler implements Listener {

    private final Map<Player, Nation> playersEditingNames = new HashMap<>();
    private final Map<Player, Nation> playersEditingCapital = new HashMap<>();
    private final Map<Player, Nation> playersEditingLeaderName = new HashMap<>();

    public AdminMainGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handlePageOne(InventoryClickEvent event) {
        if (!(event.getInventory().getName().contains("Editing Nation (Page 1):"))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);
        Nation nation = NationEditorManager.getPlayersNation(p);
        switch (event.getSlot()) {
            case 10:
                p.openInventory(AdminEditBlockGUI.getEditBlockInventory());
                NationEditorManager.playersEditType.put(p, "BLOCK");
                break;
            case 12:
                if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                    ItemStack clickedItem = event.getClickedInventory().getItem(event.getSlot());
                    if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                        playersEditingNames.put(((Player) event.getWhoClicked()).getPlayer(), nation);
                        event.getWhoClicked().closeInventory();
                        MessageUtil.sendMsgWithPrefix((Player) event.getWhoClicked(), ChatColor.GRAY + "Enter a new nation name:");
                    }
                }
                break;
            case 14:
                p.openInventory(AdminEditColorGUI.getEditColorInventory(nation));
                NationEditorManager.playersEditType.put(p, "COLOR");
                break;
            case 16:
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                NationEditorManager.playersEditType.put(p, "DELETE");
                NationEditorManager.nationsToDelete.add(nation);
                break;
            case 26:
                p.openInventory(AdminMainGUI.getAdminEditGUIPageTwo(nation));
                break;
        }
    }

    @EventHandler
    private void handlePageTwo(InventoryClickEvent event) {
        if (!(event.getInventory().getName().contains("Editing Nation (Page 2):"))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);
        Nation nation = NationEditorManager.getPlayersNation(p);

        switch (event.getSlot()) {
            case 10:
                p.openInventory(AdminEditDescriptionGUI.getDescMenu(nation));
                break;
            case 12:
                p.closeInventory();
                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Enter a new leader name: ");
                playersEditingLeaderName.put(p, nation);
                break;
            case 14:
                p.openInventory(AdminEditLeadershipGUI.getMenu());
                break;
            case 16:
                p.closeInventory();
                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Enter a new capital name: ");
                playersEditingCapital.put(p, nation);
                break;
            case 18:
                p.openInventory(AdminMainGUI.getAdminEditGUIPageOne(nation));
        }

    }


    @EventHandler
    private void showBlock(InventoryOpenEvent event) {
        if (!event.getInventory().getName().contains("Editing Nation")) return;
        if (!event.getInventory().getName().contains("1")) return;
        event.getInventory().getItem(10).setType(NationEditorManager.getPlayersNation((Player)event.getPlayer()).getItem().getType());
    }

    @EventHandler
    private void handleEditName(AsyncPlayerChatEvent event) {
        if (!playersEditingNames.containsKey(event.getPlayer())) return;
        Nation nation = playersEditingNames.get(event.getPlayer());
        String oldName = nation.getName();
        String newName = ChatColor.stripColor(event.getMessage());
        NationEditorManager.nameChanges.put(nation, newName);
        NationEditorManager.playersEditType.put(event.getPlayer(), "NAME");
        event.getPlayer().openInventory(ConfirmEditGUI.getConfirmMenu());
        playersEditingNames.remove(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    private void handleEditCapital(AsyncPlayerChatEvent event) {
        if (!playersEditingCapital.containsKey(event.getPlayer())) return;
        Nation nation = playersEditingCapital.get(event.getPlayer());
        String newName = ChatColor.stripColor(event.getMessage());
        NationEditorManager.capitalnameChanges.put(nation, newName);
        NationEditorManager.playersEditType.put(event.getPlayer(), "CAPITAL");
        event.getPlayer().openInventory(ConfirmEditGUI.getConfirmMenu());
        playersEditingCapital.remove(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    private void handleEditLeader(AsyncPlayerChatEvent event) {
        if (!playersEditingLeaderName.containsKey(event.getPlayer())) return;
        Nation nation = playersEditingLeaderName.get(event.getPlayer());
        String newName = ChatColor.stripColor(event.getMessage());
        NationEditorManager.leadernameChanges.put(nation, newName);
        NationEditorManager.playersEditType.put(event.getPlayer(), "LEADER");
        event.getPlayer().openInventory(ConfirmEditGUI.getConfirmMenu());
        playersEditingLeaderName.remove(event.getPlayer());
        event.setCancelled(true);
    }



}
