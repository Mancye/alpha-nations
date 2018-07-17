package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class TownDeleteHandler {
    /*
    When town is deleted remove from nation
     */

    @EventHandler
    private void handleTownDelete(DeleteTownEvent event) {
        Town town = new Town(event.getTownName());
        if (NationManager.getTownsNation(town) == null) return;
        NationManager.getTownsNation(town).broadcast(
                ChatColor.GRAY + "The town of " + ChatColor.RED + town.getName() + ChatColor.GRAY + " has left the nation of " + ChatColor.RED + NationManager.getTownsNation(town).getName());
        NationManager.getTownsNation(town).removeTown(town);

    }

}
