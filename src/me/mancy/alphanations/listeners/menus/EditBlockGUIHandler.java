package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class EditBlockGUIHandler implements Listener {
    private Main plugin;
    public EditBlockGUIHandler(Main main) {
        this.plugin = main;
        main.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void handleClicks(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!event.getClickedInventory().getName().contains("Set Nation's Block")) return;
        if (event.getWhoClicked() == null) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        if (event.getSlot() != 4) {
            if (event.getSlot() == 0) {

                NationEditorManager.playersEditType.remove((Player) event.getWhoClicked());
                event.getWhoClicked().openInventory(AdminMainGUI.getAdminEditGUIPageOne(NationEditorManager.getPlayersNation((Player) event.getWhoClicked())));
            }
            event.setCancelled(true);
        }

    }

    @EventHandler
    private void setBlock(InventoryCloseEvent event) {
        if (event.getInventory() == null) return;
        if (!event.getInventory().getName().contains(ChatColor.RED + "Set Nation's Block")) return;
        if (!(NationEditorManager.playersEditType.containsKey((Player)event.getPlayer()))) {
            System.out.println("NOT HERE");
            return;
        } else {
            System.out.println("STILL HERE");
        }
        if (event.getPlayer() == null) return;
        Nation n = NationEditorManager.getPlayersNation((Player)event.getPlayer());
        if (event.getInventory().getItem(4) != null) {
            NationEditorManager.blockChanges.put(n, event.getInventory().getItem(4));
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, () -> event.getPlayer().openInventory(ConfirmEditGUI.getConfirmMenu()), 10L);
        } else {
            MessageUtil.sendMsgWithPrefix((Player) event.getPlayer(), ChatColor.RED + "No block set!");
        }

    }

    @EventHandler
    private void showCorrectBlock(InventoryOpenEvent event) {
        if (event.getInventory() == null) return;
        if (!event.getInventory().getName().contains(ChatColor.RED + "Set Nation's Block")) return;
        if (event.getPlayer() == null) return;
        Nation n = NationEditorManager.getPlayersNation((Player)event.getPlayer());
        event.getInventory().setItem(4, n.getItem());
    }


}
