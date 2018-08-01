package me.mancy.alphanations.listeners.menus;

import com.nametagedit.plugin.NametagEdit;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ConfirmEditGUIHandler implements Listener {

    public ConfirmEditGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onConfirmation(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Confirm Your Changes"))) return;
        if (NationEditorManager.getPlayersNation((Player)event.getWhoClicked()) == null) return;
        if (!NationEditorManager.playersEditType.containsKey((Player) event.getWhoClicked())) return;
        event.setCancelled(true);
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
                event.getWhoClicked().closeInventory();
                MessageUtil.sendMsgWithPrefix((Player)event.getWhoClicked(), ChatColor.GREEN + "Changes Saved!");
                break;
            case 15:
                event.getWhoClicked().closeInventory();
                MessageUtil.sendMsgWithPrefix((Player)event.getWhoClicked(), ChatColor.RED + "Changes not saved!");
                break;
        }

    }

}
