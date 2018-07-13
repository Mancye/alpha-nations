package me.mancy.alphanations.managers;

import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

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

        if (nationToRemove != null) {
            plugin.nationsConfig.set((nationList.indexOf(nationToRemove) + 1) + ". name", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            plugin.nationsConfig.set((nationList.indexOf(nationToRemove) + 1) + ". members", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            plugin.nationsConfig.set((nationList.indexOf(nationToRemove) + 1) + ". menudescription", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            nationList.remove(nationToRemove);
            for (int x = 1; x <= nationList.size(); x++) {
                plugin.nationsConfig.set(x + ". name", null);
                plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
                plugin.nationsConfig.set(x + ". members", null);
                plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
                plugin.nationsConfig.set(x + ". menudescription", null);
                plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            }
            plugin.saveNations();
            plugin.loadNations();
        }

    }

    public static void removeNation(Nation nation) {
        if (!nationList.contains(nation)) return;
        if (nation != null) {
            plugin.nationsConfig.set((nationList.indexOf(nation) + 1) + ". name", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            plugin.nationsConfig.set((nationList.indexOf(nation) + 1) + ". members", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            plugin.nationsConfig.set((nationList.indexOf(nation) + 1) + ". menudescription", null);
            plugin.saveCustomYml(plugin.nationsConfig, plugin.nationsFile);
            nationList.remove(nation);
        }
    }

}
