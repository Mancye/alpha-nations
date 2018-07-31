package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownDeleteHandler implements Listener {

    public TownDeleteHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleTownDelete(TownRemoveResidentEvent event) {
       Town town = event.getTown();
       if  (town == null) return;
        if (event.getResident() != town.getMayor()) return;
        if (NationManager.getTownsNation(town) == null) return;
        NationManager.getTownsNation(town).broadcast(
                ChatColor.GRAY + "The town of " + ChatColor.RED + town.getName() + ChatColor.GRAY + " has left the nation of " + ChatColor.RED + NationManager.getTownsNation(town).getName());
        NationManager.getTownsNation(town).removeTown(town);

    }

}
