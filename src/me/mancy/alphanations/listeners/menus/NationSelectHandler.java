package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.listeners.misc.JoinNation;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class NationSelectHandler implements Listener {
    private Main plugin;
    public NationSelectHandler(Main main) {
        this.plugin = main;
        main.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onSelect(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if  (!(event.getClickedInventory().getName().contains(ChatColor.RED + "Choose a nation"))) return;
        if (event.getClickedInventory().getName().contains("to edit")) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();

        for (Nation nation : NationManager.getNationList()) {
            if (event.getClickedInventory().getItem(event.getSlot()) != null) {
                if (event.getClickedInventory().getItem(event.getSlot()).hasItemMeta() && event.getClickedInventory().getItem(event.getSlot()).getItemMeta().hasDisplayName()) {
                    if (ChatColor.stripColor(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).equalsIgnoreCase(nation.getName())) {
                        nation.addMember(p);
                        JoinNation.choosing.remove(p);
                        MessageUtil.sendMsgWithPrefix(p,ChatColor.GRAY + "You have joined " + ChatColor.GREEN + nation.getName());
                        p.closeInventory();
                    }
                }
            }
        }

    }

    @EventHandler
    private void cancelClose(InventoryCloseEvent event) {
        if (event.getInventory() == null) return;
        if  (!(event.getInventory().getName().contains(ChatColor.RED + "Choose a nation"))) return;
        if (event.getInventory().getName().contains("edit")) return;
        if (!(event.getPlayer() instanceof Player)) return;
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            Player p = (Player) event.getPlayer();
            if (NationManager.getPlayersNation(p) == null) {
                p.openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
            }
        }, 5L);

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
        AdminMainGUI.getAdminEditGUIPageOne(selectedNation).getItem(10).setType(selectedNation.getItem().getType());
        p.openInventory(AdminMainGUI.getAdminEditGUIPageOne(selectedNation));
    }

}
