package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownCreateHandler implements Listener {

    /*
    When a new town is created, automatically add to creator's nation
     */

    public TownCreateHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onTownCreate(NewTownEvent event) {
        Town town = event.getTown();

        if (((Player) event.getTown().getMayor()) != null) {
            Player p = (Player) event.getTown().getMayor();
            Nation nation = NationManager.getPlayersNation(p);

            nation.addTown(town);
            nation.broadcast(ChatColor.GRAY + "The town of " + ChatColor.GREEN + town.getName() +
                            ChatColor.GRAY + " has joined the nation of " + ChatColor.GREEN + nation.getName());
        }


    }

}
