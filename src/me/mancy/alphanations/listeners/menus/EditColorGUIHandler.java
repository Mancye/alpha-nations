package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationEditorManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditColorGUIHandler implements Listener {

    private Main plugin;
    public EditColorGUIHandler(Main main) {
        this.plugin = main;
        main.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void handleEditColor(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (NationEditorManager.getPlayersNation((Player) event.getWhoClicked()) == null) return;
        if (!event.getClickedInventory().getName().contains(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getColor() + "Select Nation Color")) return;
        event.setCancelled(true);
        if (event.getClickedInventory().getItem(event.getSlot()) == null) return;
        if (!event.getClickedInventory().getItem(event.getSlot()).hasItemMeta()) return;
        if (!event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) return;


        switch (event.getSlot()) {
            case 0: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.RED);
            break;
            case 1: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_RED);

            break;
            case 2: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.GOLD);
            break;
            case 3: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.YELLOW);

            break;
            case 4: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.GREEN);
            break;
            case 5: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_GREEN);

            break;
            case 6: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.AQUA);
            break;
            case 7: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_AQUA);
            break;
            case 8: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.BLUE);
            break;
            case 10: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_BLUE);

            break;
            case 11: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.LIGHT_PURPLE);
            break;
            case 12: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_PURPLE);
            break;

            case 13: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.WHITE);
            break;
            case 14: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.GRAY);
            break;
            case 15: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_GRAY);
            break;
            case 16: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.BLACK);
            break;
            case 18: NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                event.getWhoClicked().openInventory(AdminMainGUI.getAdminEditGUIPageOne(NationEditorManager.getPlayersNation((Player) event.getWhoClicked())));
            return;

            default:
                return;
        }
        event.getWhoClicked().openInventory(ConfirmEditGUI.getConfirmMenu());

    }

}
