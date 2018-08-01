package me.mancy.alphanations.listeners.menus;

import com.nametagedit.plugin.NametagEdit;
import me.mancy.alphanations.gui.AdminMainGUI;
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

public class NationSelectHandler implements Listener {

    public NationSelectHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onSelect(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if  (!(ChatColor.stripColor(event.getClickedInventory().getTitle()).equalsIgnoreCase("choose a nation"))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();

        for (Nation nation : NationManager.getNationList()) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                if (event.getClickedInventory().getItem(event.getSlot()).hasItemMeta() && event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) {
                    if (ChatColor.stripColor(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).equalsIgnoreCase(nation.getName())) {
                        nation.addMember(p);
                        MessageUtil.sendMsgWithPrefix(p,ChatColor.GRAY + "You have joined " + ChatColor.GREEN + nation.getName());
                        String prefix;
                        String suffix;
                        if (NametagEdit.getApi().getNametag(p) != null) {
                            prefix = NametagEdit.getApi().getNametag(p).getPrefix().substring(0, 9) + NationManager.getPlayersNation(p).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
                            suffix = NationManager.getPlayersNation(p).getColor() + "▶";
                        } else {
                            prefix = NationManager.getPlayersNation(p).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
                            suffix = NationManager.getPlayersNation(p).getColor() + "▶";
                        }
                        p.performCommand("nte player " + p.getName() + " clear");
                        p.performCommand("nte player " + p.getName() + " prefix " + prefix);
                        p.performCommand("nte player " + p.getName() + " suffix " + suffix);
                        p.closeInventory();
                    }
                }
            }
        }

    }

    @EventHandler
    private void onAdminSelect(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if  (!(event.getInventory().getName().contains("Choose a nation to edit"))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();
        Nation selectedNation = null;
        for (Nation nation : NationManager.getNationList()) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                if (event.getClickedInventory().getItem(event.getSlot()).hasItemMeta() && event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) {
                    if (ChatColor.stripColor(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).equalsIgnoreCase(nation.getName())) {
                        selectedNation = nation;
                        MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Now editing nation: " + ChatColor.RED + nation.getName());
                        NationEditorManager.setPlayersNation(p, nation);
                    }
                }
            }
        }
        if (selectedNation == null) return;
        p.openInventory(AdminMainGUI.getAdminEditGUI(selectedNation));
    }

}
