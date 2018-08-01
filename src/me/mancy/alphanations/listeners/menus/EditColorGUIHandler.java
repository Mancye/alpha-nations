package me.mancy.alphanations.listeners.menus;

import com.nametagedit.plugin.NametagEdit;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationEditorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class EditColorGUIHandler implements Listener {

    public EditColorGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleEditColor(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Select Nation Color"))) return;
        event.setCancelled(true);
        if (event.getClickedInventory().getItem(event.getSlot()) == null) return;
        if (!event.getClickedInventory().getItem(event.getSlot()).hasItemMeta()) return;
        if (!event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) return;
        if (NationEditorManager.getPlayersNation((Player) event.getWhoClicked()) == null) return;

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
            case 9: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_BLUE);

            break;
            case 10: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.LIGHT_PURPLE);
            break;
            case 11: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_PURPLE);
            break;

            case 12: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.WHITE);
            break;
            case 13: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.GRAY);
            break;
            case 14: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.DARK_GRAY);
            break;
            case 15: NationEditorManager.colorChanges.put(NationEditorManager.getPlayersNation((Player) event.getWhoClicked()), ChatColor.BLACK);
            break;

            default:
                return;
        }
        for (String sp : NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getMembers()) {
            Player p = Bukkit.getPlayer(UUID.fromString(sp));
            String prefix;
            String suffix;
            if (NametagEdit.getApi().getNametag(p) != null) {
                prefix = NametagEdit.getApi().getNametag(p).getPrefix().substring(0, 9) + NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
                suffix = NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getColor() + "▶";
            } else {
                prefix = NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
                suffix = NationEditorManager.getPlayersNation((Player) event.getWhoClicked()).getColor() + "▶";
            }
            p.performCommand("nte player " + p.getName() + " clear");
            p.performCommand("nte player " + p.getName() + " prefix " + prefix);
            p.performCommand("nte player " + p.getName() + " suffix " + suffix);
        }

        event.getWhoClicked().openInventory(ConfirmEditGUI.getConfirmMenu());

    }

}
