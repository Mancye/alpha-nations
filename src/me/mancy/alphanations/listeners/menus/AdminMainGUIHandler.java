package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminEditBlockGUI;
import me.mancy.alphanations.gui.AdminEditColorGUI;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.gui.NationSelectionGUI;
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
        String nationName = ChatColor.stripColor(event.getInventory().getName().substring(20));
        Nation nation = NationManager.getNation(nationName);
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
                p.openInventory(AdminEditColorGUI.getEditColorInventory());
                NationEditorManager.playersEditType.put(p, "COLOR");
                break;
            case 16:
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                NationEditorManager.playersEditType.put(p, "DELETE");
                NationEditorManager.nationsToDelete.add(nation);
                break;
        }
    }





    @EventHandler
    private void handleEditNameInput(AsyncPlayerChatEvent event) {
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

}
