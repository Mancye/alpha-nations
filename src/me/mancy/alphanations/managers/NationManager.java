package me.mancy.alphanations.managers;

import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import org.bukkit.Bukkit;
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

    private static List<Nation> nationList = new ArrayList<>();

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
        List<Player> membersToRemove = new ArrayList<>();
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                nationToRemove = nation;
                nationToRemove.broadcast(ChatColor.RED + "Your nation has been deleted please use /nation or /n to select a new one!");
                for (String uuidStr : nationToRemove.getMembers()) {
                    UUID uuid = UUID.fromString(uuidStr);
                    Player p = Bukkit.getPlayer(uuid);
                    membersToRemove.add(p);
                }
            }
        }
        for (Player p : membersToRemove) {
            nationToRemove.removeMember(p);
        }
        if (nationToRemove != null) nationList.remove(nationToRemove);
    }

    public static void removeNation(Nation nation) {
        if (!nationList.contains(nation)) return;
        if (nation != null) {
            List<Player> membersToRemove = new ArrayList<>();
            nation.broadcast(ChatColor.RED + "Your nation has been deleted please use /nation or /n to select a new one!");
            for (String uuidStr : nation.getMembers()) {
                UUID uuid = UUID.fromString(uuidStr);
                Player p = Bukkit.getPlayer(uuid);
                membersToRemove.add(p);
            }
            for (Player p : membersToRemove) {
                nation.removeMember(p);
            }
            nationList.remove(nation);
        }
    }

    public static Nation getPlayersNation(Player p) {
        if (p == null) return null;
        UUID uuid = p.getUniqueId();
        Nation nation;

        for (Nation n : nationList) {
            if (n.getMembers().contains(uuid.toString())) {
                nation = n;
                return nation;
            }
        }
        return null;
    }

    public static Nation getTownsNation(Town town) {

        for (Nation n : nationList) {
            if (n.getTowns().contains(town)) {
                return n;
            }
        }

        return null;
    }

    public static int getAmountNations() {
        return getNationList().size();
    }



}
