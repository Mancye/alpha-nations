package me.mancy.alphanations.listeners.misc;

import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChatHandler implements Listener {
    public ChatHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void setChatSettings(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {

        }
    }

}