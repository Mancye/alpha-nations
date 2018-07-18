package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownDeleteHandler implements Listener {
    /*
    When town is deleted remove from nation
     */

    public TownDeleteHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleTownDelete(DeleteTownEvent event) {
        Town town = new Town(event.getTownName());
        if (NationManager.getTownsNation(town) == null) return;
        NationManager.getTownsNation(town).broadcast(
                ChatColor.GRAY + "The town of " + ChatColor.RED + town.getName() + ChatColor.GRAY + " has left the nation of " + ChatColor.RED + NationManager.getTownsNation(town).getName());
        NationManager.getTownsNation(town).removeTown(town);

    }

}
