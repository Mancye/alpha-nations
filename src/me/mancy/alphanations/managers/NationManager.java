package me.mancy.alphanations.managers;

import me.mancy.alphanations.main.Nation;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class NationManager {

    public static List<Nation> nationList = new ArrayList<>();

    public static List<Nation> getNationList() {
        return nationList;
    }

    public static Nation getNation(String nationName) {
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                return nation;
            }
        }
        return null;
    }

    public static boolean doesNationExist(Nation nation) {
        return nationList.contains(nation);
    }

    public static boolean doesNationExist(String nationName) {
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                return true;
            }
        }
        return false;
    }

    public static void addNation(Nation nation) {
        if (!(nationList.contains(nation))) {
           nationList.add(nation);
        } else {
            System.out.println(ChatColor.RED + "alphaNATIONS ERROR: addNation called, nation already contained in list!");
        }
    }

    public static void removeNation(String nationName) {
        for (Nation nation : nationList) {
            if (nation.getName().equalsIgnoreCase(nationName)) {
                nationList.remove(nation);
            }
        }
    }

    public static void removeNation(Nation nation) {
        nationList.remove(nation);
    }

}
