package me.mancy.alphanations.main;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.commands.BaseCommand;
import me.mancy.alphanations.listeners.menus.*;
import me.mancy.alphanations.listeners.misc.JoinNation;
import me.mancy.alphanations.listeners.misc.LoadTags;
import me.mancy.alphanations.listeners.towny.TownCreateHandler;
import me.mancy.alphanations.listeners.towny.TownDeleteHandler;
import me.mancy.alphanations.listeners.towny.TownJoinHandler;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[alphaNATIONS] Version " + this.getDescription().getVersion() + " Enabled Successfully");
    }

    @Override
    public void onDisable() {
        saveNations();
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[alphaNATIONS] Version " + this.getDescription().getVersion() + " Disabled Successfully");
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
                nationsConfig.set(x + ". capitallocation", n.getCapital());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". color", n.getColor().toString());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". leader", n.getLeaderName());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". leadershiptype", n.getLeadershipType());
                saveCustomYml(nationsConfig, nationsFile);
                nationsConfig.set(x + ". capitalname", n.getCapitalName());
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
            final List<String> members = new ArrayList<>();
            for (String strUUID : nationsConfig.getStringList(x + ". members")) {
                UUID uuid = UUID.fromString(strUUID);
                if (!members.contains(uuid.toString())) members.add(uuid.toString());
            }
            if (!nationsConfig.contains(x + ". menudescription")) nationsConfig.set(x + ". menudescription", null);
            final List<String> menuDescription = nationsConfig.getStringList(x + ". menudescription");
            if (!nationsConfig.contains(x + ". item")) nationsConfig.set(x + ". item", null);
            final ItemStack item = new ItemStack(Material.getMaterial(nationsConfig.getString(x + ". item")));
            if (!nationsConfig.contains(x + ". capitallocation")) nationsConfig.set(x + ". capitallocation", null);
            final Location capital = (Location) nationsConfig.get(x + ". capitallocation");
            if (!nationsConfig.contains(x + ". color")) nationsConfig.set(x + ". color", null);
            final ChatColor color = ChatColor.getByChar(nationsConfig.get(x + ". color").toString().charAt(1));
            if (!nationsConfig.contains(x + ". leader")) nationsConfig.set(x + ". leader", null);
            String leaderName = nationsConfig.getString(x + ". leader");
            if (!nationsConfig.contains(x + ". leadershiptype")) nationsConfig.set(x + ". leadershiptype", null);
            String leadershiptype = nationsConfig.getString(x + ". leadershiptype");
            if (!nationsConfig.contains(x + ". capitalname")) nationsConfig.set(x + ". capitalname", null);
            String capitalName = nationsConfig.getString(x + ". capitalname");
            Nation nation = new Nation(name, members, capital);
            nation.setMenuDescription(menuDescription);
            nation.setItem(item);
            nation.setColor(color);
            nation.setCapitalName(capitalName);
            nation.setLeaderName(leaderName);
            nation.setLeadershipType(leadershiptype);
            NationManager.addNation(nation);
            try {
            for (String pUUID : nation.getMembers()) {
                Player player = Bukkit.getPlayer(UUID.fromString(pUUID));
                Resident r = null;
                if (player != null) {
                    r = TownyUniverse.getDataSource().getResident(player.getName());

                }
                for (Town town : TownyUniverse.getDataSource().getTowns()) {
                    if (town.getMayor().equals(r)) {
                        nation.addTown(town);
                        System.out.println("[PA:Nations] Added town: " + town.getName() + " To nation: " + nation.getName());
                    }
                }
            }
            } catch (NotRegisteredException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PA NATIONS ERROR: TOWNY FAILED PLUGIN SHUTTING DOWN");
            }

        }
    }

    private void registerCommands() {
        this.getCommand("nations").setExecutor(new BaseCommand());
    }

    private void registerEvents() {
        //managers
        new NationManager(this);

        //menus
        new NationSelectHandler(this);
        new AdminMainGUIHandler(this);
        new ConfirmEditGUIHandler(this);
        new EditBlockGUIHandler(this);
        new EditColorGUIHandler(this);

        //misc
        new JoinNation(this);
        //new LoadTags(this);

        //towny
        new TownJoinHandler(this);
        new TownCreateHandler(this);
        new TownDeleteHandler(this);

    }

    private void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
