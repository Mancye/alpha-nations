package me.mancy.alphanations.listeners;

import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import me.mancy.alphanations.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownJoinHandler implements Listener {

    public TownJoinHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void townJoinRestric(TownAddResidentEvent event) {
        if (event.getResident() == null) return;
        Player p = (Player) event.getResident();


    }


}
