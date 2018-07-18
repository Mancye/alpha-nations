package me.mancy.alphanations.listeners.misc;

import me.mancy.alphanations.gui.NationSelectionGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class JoinNation implements Listener {

    public JoinNation(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
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
                    event.getPlayer().openInventory(NationSelectionGUI.getPlayerNationSelectionInventory());
                }
            }
        }
    }

}
