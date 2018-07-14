package me.mancy.alphanations.listeners;

import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {
    public ChatHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void setChatColor(AsyncPlayerChatEvent event) {
        if (NationManager.getPlayersNation(event.getPlayer()) == null) return;

        Color color =

    }

}
