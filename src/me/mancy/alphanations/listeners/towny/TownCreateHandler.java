package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

public class TownCreateHandler implements Listener {

    /*
    When a new town is created, automatically add to creator's nation
     */

    private Main plugin;

    public TownCreateHandler(Main main) {
        this.plugin = main;
        main.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Town town;

    @EventHandler
    private void onTownCreate(NewTownEvent event) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        town = event.getTown();
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            Player p;
            if (event.getTown().getMayor() != null) {
                try {
                    p = TownyUniverse.getPlayer(event.getTown().getMayor());
                    Nation nation = NationManager.getPlayersNation(p);
                    if (NationManager.getPlayersNation(p) == null) return;
                    nation.addTown(town);
                    nation.broadcast(ChatColor.GRAY + "The town of " + ChatColor.GREEN + town.getName() +
                            ChatColor.GRAY + " has joined the nation of " + nation.getColor() + nation.getName());
                } catch (TownyException e) {
                    e.printStackTrace();
                }
            }

        }, 5L);



    }

}
