package me.mancy.alphanations.listeners;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.exceptions.EmptyTownException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
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
        if (NationManager.getPlayersNation(p) == null) return;
        Nation nation = NationManager.getPlayersNation(p);
        if (nation.doesContainTown(event.getTown())) {

        } else {
            if (event.getTown().hasResident(event.getResident())) {
                try {
                    Resident r = TownyUniverse.getDataSource().getResident(p.getName());
                    try {
                        event.getTown().removeResident(r);
                        p.sendMessage(ChatColor.RED + "You may only join towns that belong to the nation of " + nation.getName());
                    } catch (EmptyTownException e){
                        e.printStackTrace();
                    }

                } catch (NotRegisteredException exception) {
                    exception.printStackTrace();
                }

            }
        }


    }


}
