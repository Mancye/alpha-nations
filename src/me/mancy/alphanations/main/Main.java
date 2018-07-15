package me.mancy.alphanations.main;

import me.mancy.alphanations.commands.BaseCommand;
import me.mancy.alphanations.listeners.AdminGUIHandler;
import me.mancy.alphanations.listeners.ChatHandler;
import me.mancy.alphanations.listeners.NationSelectHandler;
import me.mancy.alphanations.listeners.TownJoinHandler;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    private File nationsFile = new File(this.getDataFolder() + "/nations.yml");
    private FileConfiguration nationsConfig = YamlConfiguration.loadConfiguration(nationsFile);

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        loadNations();
        System.out.println(ChatColor.GREEN + "[alphaNATIONS] Plugin Enabled Successfully");
    }

    @Override
    public void onDisable() {
        saveNations();
        System.out.println(ChatColor.RED + "[alphaNATIONS] Plugin Disabled Successfully");
    }

    private void saveNations() {
        nationsConfig.set("Amount of Nations", NationManager.nationList.size());
        saveCustomYml(nationsConfig, nationsFile);
        int x = 1;
        for (Nation n : NationManager.getNationList()) {
            if (x <= NationManager.getNationList().size()) {
                nationsConfig.set(x + ". name", n.getName());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". members", n.getMembers());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". menudescription", n.getMenuDescription());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". item", n.getItem().getType().name());
                saveCustomYml(nationsConfig, nationsFile);
                x++;
            } else {
                break;
            }
        }

    }

    private void loadNations() {
        if (!nationsConfig.contains("Amount of Nations")) {
            nationsConfig.set("Amount of Nations", 0);
            saveCustomYml(nationsConfig, nationsFile);
        }
        NationManager.getNationList().clear();
        int amtNations = nationsConfig.getInt("Amount of Nations");
        for (int x = 1; x <= amtNations; x++) {
            final String name = nationsConfig.getString(x + ". name");
            final List<UUID> members = new ArrayList<>();
            for (String strUUID : nationsConfig.getStringList(x + ". members")) {
                UUID uuid = UUID.fromString(strUUID);
                if (!members.contains(uuid)) members.add(uuid);
            }
            if (!nationsConfig.contains(x + ". menudescription")) nationsConfig.set(x + ". menudescription", null);
            final List<String> menuDescription = nationsConfig.getStringList(x + ". menudescription");
            if (!nationsConfig.contains(x + ". item")) nationsConfig.set(x + ". item", null);
            final ItemStack item = new ItemStack(Material.getMaterial(nationsConfig.getString(x + ". item")));
            Nation nation = new Nation(name, members);
            nation.setMenuDescription(menuDescription);
            nation.setItem(item);
            NationManager.addNation(nation);
        }
    }

    private void registerCommands() {
        this.getCommand("nations").setExecutor(new BaseCommand());
    }

    private void registerEvents() {
        new NationManager(this);
        new NationSelectHandler(this);
        new AdminGUIHandler(this);
        new ChatHandler(this);
        new TownJoinHandler(this);
    }

    public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
