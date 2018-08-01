package me.mancy.alphanations.listeners.misc;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.api.events.NametagFirstLoadedEvent;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LoadTags implements Listener {

    public LoadTags(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onTagLoad(NametagFirstLoadedEvent event) {
        String prefix;
        String suffix;
        if (NationManager.getPlayersNation(event.getPlayer()) == null) return;
        if (NametagEdit.getApi().getNametag(event.getPlayer()).getPrefix().equalsIgnoreCase("") && NametagEdit.getApi().getNametag(event.getPlayer()).getSuffix().equalsIgnoreCase("")) {
            prefix = NametagEdit.getApi().getNametag(event.getPlayer()).getPrefix().substring(0, 9) + NationManager.getPlayersNation(event.getPlayer()).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
            suffix = NationManager.getPlayersNation(event.getPlayer()).getColor() + "▶";
        } else {
            prefix = NationManager.getPlayersNation(event.getPlayer()).getColor() + " ◀" + ChatColor.COLOR_CHAR + "7";
            suffix = NationManager.getPlayersNation(event.getPlayer()).getColor() + "▶";
        }
        event.getPlayer().performCommand("nte player " + event.getPlayer().getName() + " clear");
        event.getPlayer().performCommand("nte player " + event.getPlayer().getName() + " prefix " + prefix);
        event.getPlayer().performCommand("nte player " + event.getPlayer().getName() + " suffix " + suffix);
    }

}
