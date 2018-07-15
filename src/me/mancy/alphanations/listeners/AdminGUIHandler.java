package me.mancy.alphanations.listeners;

import me.mancy.alphanations.gui.AdminNationGUI;
import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminGUIHandler implements Listener {

    private final Map<Player, Nation> playersEditingNames = new HashMap<>();

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
                p.openInventory(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_BLOCK));
                break;
            }
            case 12: {
                p.openInventory(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_NAME));
                break;
            }
            case 14: {
                p.openInventory(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_COLOR));
                break;
            }
            case 16: {
                p.openInventory(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_DELETE));
                break;
            }
        }
    }

    @EventHandler
    private void handleNationEditSelectionClicks(InventoryClickEvent event) {
        if (event.getInventory().equals(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_BLOCK))) {
            event.getWhoClicked().openInventory(AdminNationGUI.getEditBlockInventory());
            return;
        } else if (event.getInventory().equals(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_NAME))) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                ItemStack clickedItem = event.getClickedInventory().getItem(event.getSlot());
                if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                    String name = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());
                    Nation n = NationManager.getNation(name);
                    playersEditingNames.put((Player) event.getWhoClicked(), n);
                    ((Player) event.getWhoClicked()).closeInventory();
                    ((Player) event.getWhoClicked()).sendMessage(ChatColor.GOLD + "ENTER NEW NATION NAME: ");
                }
            }
            return;
        } else if (event.getInventory().equals(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_COLOR))) {
            return;
        } else if (event.getInventory().equals(NationSelectionGUI.getSelectionInventory(NationSelectionGUI.NationSelectType.ADMIN_DELETE))) {
            return;
        } else {
            return;
        }
    }

    @EventHandler
    private void handleEditBlockClicks(InventoryClickEvent event) {
        if (!event.getClickedInventory().equals(AdminNationGUI.getEditBlockInventory())) return;
        if (event.getSlot() != 4) event.setCancelled(true);
    }

    @EventHandler
    private void setBlock(InventoryCloseEvent event) {
        if (!event.getInventory().equals(AdminNationGUI.getEditBlockInventory())) return;
        if (event.getInventory().getItem(4) == null) {

        } else {

        }
    }

    @EventHandler
    private void handleEditNameInput(AsyncPlayerChatEvent event) {
        if (!playersEditingNames.containsKey(event.getPlayer())) return;
        String newName = ChatColor.stripColor(event.getMessage());
        playersEditingNames.get(event.getPlayer()).setName(newName);
        playersEditingNames.remove(event.getPlayer());
    }

}
