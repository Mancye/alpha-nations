package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditBlockGUI;
import me.mancy.alphanations.gui.AdminEditColorGUI;
import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AdminMainGUIHandler implements Listener {

    private final Map<Player, Nation> playersEditingNames = new HashMap<>();

    public AdminMainGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleMainGUIClicks(InventoryClickEvent event) {
        if (!(event.getInventory().getName().contains("Editing Nation: "))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);
        String nationName = ChatColor.stripColor(event.getInventory().getName().substring(18));
        Nation nation = NationManager.getNation(nationName);
        p.sendMessage("DEBUG: Nation name found = " + nationName);
        switch (event.getSlot()) {

            case 10: {
                p.openInventory(AdminEditBlockGUI.getEditBlockInventory());
                break;
            }
            case 12: {
                if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                    ItemStack clickedItem = event.getClickedInventory().getItem(event.getSlot());
                    if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                        playersEditingNames.put((Player) event.getWhoClicked(), nation);
                        ((Player) event.getWhoClicked()).closeInventory();
                        ((Player) event.getWhoClicked()).sendMessage(ChatColor.GOLD + "ENTER NEW NATION NAME: ");
                    }
                }
                break;
            }
            case 14: {
                p.openInventory(AdminEditColorGUI.getEditColorInventory());
                break;
            }
            case 16: {
                p.openInventory(NationSelectionGUI.getAdminNationDeleteInventory());
                break;
            }
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
