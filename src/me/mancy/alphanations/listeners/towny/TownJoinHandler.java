package me.mancy.alphanations.listeners.towny;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.exceptions.EmptyTownException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

public class TownJoinHandler implements Listener, Runnable {

    private Main plugin;
    public TownJoinHandler(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private Player p;

    @EventHandler
    private void townJoinRestrict(TownAddResidentEvent event) {
        if (event.getResident() == null) return;
        if (event.getTown().getMayor() == null) return;
        if (event.getResident() == event.getTown().getMayor()) return;

        try {
            p = TownyUniverse.getPlayer(event.getResident());
        } catch (TownyException e) {
            e.printStackTrace();
        }
        if (NationManager.getPlayersNation(p) == null) return;
        Nation nation = NationManager.getPlayersNation(p);
        if (!nation.doesContainTown(event.getTown())) {
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, () -> {
                if (event.getTown().hasResident(event.getResident())) {

                    try {
                        Resident r = TownyUniverse.getDataSource().getResident(p.getName());
                        try {
                            event.getTown().removeResident(r);
                            MessageUtil.sendMsgWithPrefix(p, ChatColor.RED + "You may only join towns that belong to the nation of " + nation.getColor() + nation.getName());
                        } catch (EmptyTownException e){
                            e.printStackTrace();
                        }

                    } catch (NotRegisteredException exception) {
                        exception.printStackTrace();
                    }

                }
            }, 20L);

        }


    }


    @Override
    public void run() {

    }
}
