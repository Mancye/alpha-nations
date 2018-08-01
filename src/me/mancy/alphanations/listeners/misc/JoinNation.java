package me.mancy.alphanations.listeners.misc;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class JoinNation implements Listener {
    private Main plugin;
    public JoinNation(Main main) {
        this.plugin = main;
        main.getServer().getPluginManager().registerEvents(this, plugin);
    }
    /*
    Prompt user with nation selection upon nether portal entrance
     */
    @EventHandler
    private void selectNation(PlayerTeleportEvent event) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            if (event.getFrom().getWorld().getName().equalsIgnoreCase("tutorial")) {
                if (NationManager.getPlayersNation(event.getPlayer()) == null) {
                    MessageUtil.sendMsgWithPrefix(event.getPlayer(), ChatColor.GRAY + "Select a nation to join!");
                    if (NationManager.getAmountNations() <= 0) {
                        MessageUtil.sendMsgWithPrefix(event.getPlayer(), ChatColor.RED + "Error: No nations created!");
                        return;
                    }
                    event.getPlayer().openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                }
            }
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            if (NationManager.getPlayersNation(event.getPlayer()) == null) {
                if (NationManager.getAmountNations() > 0) {
                    event.getPlayer().openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                }
            }
        }, 10L);
    }

}
