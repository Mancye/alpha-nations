package me.mancy.alphanations.managers;

import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NationManager {

    private static Main plugin;

    public NationManager(Main main) {
        this.plugin = main;
    }

    public static List<Nation> nationList = new ArrayList<>();

    public static List<Nation> getNationList() {
        return nationList;
    }

    public static Nation getNation(String nationName) {
        if (nationName == null) return null;
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                return nation;
            }
        }
        return null;
    }

    public static boolean doesNationExist(Nation nation) {
        if (nation == null) return false;
        return nationList.contains(nation);
    }

    public static boolean doesNationExist(String nationName) {
        if (nationName == null) return false;
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                return true;
            }
        }
        return false;
    }

    public static void addNation(Nation nation) {
        if (nation == null) return;
        if (!(nationList.contains(nation))) {
           nationList.add(nation);
        } else {
            System.out.println(ChatColor.RED + "alphaNATIONS ERROR: addNation called, nation already contained in list!");
        }
    }

    public static void removeNation(String nationName) {
        if (nationName == null) return;
        Nation nationToRemove = null;
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                nationToRemove = nation;
            }
        }
        if (nationToRemove != null) nationList.remove(nationToRemove);
    }

    public static void removeNation(Nation nation) {
        if (!nationList.contains(nation)) return;
        if (nation != null) {
            nationList.remove(nation);
        }
    }

    public static Nation getPlayersNation(Player p) {
        if (p == null) return null;
        UUID uuid = p.getUniqueId();
        Nation nation;

        for (Nation n : nationList) {
            if (n.getMembers().contains(uuid)) {
                nation = n;
                return nation;
            }
        }
        return null;
    }

    public static Nation getTownsNation(Town town) {
        if (town == null) return null;

        for (Nation n : nationList) {
            if (n.getTowns().contains(town)) {
                return n;
            }
        }

        return null;
    }



}
