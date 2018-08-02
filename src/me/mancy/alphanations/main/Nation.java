package me.mancy.alphanations.main;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Nation {

    private String name;
    private List<String> members;
    private List<String> menuDescription = new ArrayList<>();
    private List<Town> towns = new ArrayList<>();
    private Location capital;
    private ChatColor color;
    private ItemStack item;



    private String leaderName;
    private String leadershipType;
    private String capitalName;

    public Nation(String name, List<String> members, Location capital) {
        this.name = name;
        if (members == null) members = new ArrayList<>();
        this.members = members;
        this.capital = capital;
        this.item = new ItemStack(Material.GRASS_BLOCK);
        this.leadershipType = " ";
        this.leaderName = " ";
        this.capitalName = " ";
    }
    public ItemStack getItem() {
        if (this.item == null) {
            this.item = new ItemStack(Material.GRASS_BLOCK);
        }
        return this.item;
    }

    public void setItem(ItemStack item) {
        if (item == null) {
            this.item = new ItemStack(Material.GRASS_BLOCK);
        }
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public Location getCapital() {
        return this.capital;
    }

    public void setCapital(Location loc) {
        if (loc != null)
        this.capital = loc;
    }

    public void setName(String newName) {
        if (newName == null) {
            this.name = "Undefined";
            return;
        }
        this.name = newName;
    }

    public List<String> getMembers() {
        if (this.members == null) {
            this.menuDescription = new ArrayList<>();
        }
        return members;
    }

    public void addMember(Player player) {
        if (player == null) return;
        if  (members.contains(player.getUniqueId().toString())) return;
        members.add(player.getUniqueId().toString());

        try {
            Town town;
            if (TownyUniverse.getDataSource().getResident(player.getName()).hasTown()) {
                town = TownyUniverse.getDataSource().getResident(player.getName()).getTown();
            } else {
                return;
            }
            if (town.getMayor().equals(TownyUniverse.getDataSource().getResident(player.getName()))) {
                addTown(town);
                broadcast(ChatColor.GRAY + "The town of " + ChatColor.GREEN + town.getName() +
                        ChatColor.GRAY + " has joined the nation of " + ChatColor.GREEN + getName());
            }
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }
        player.teleport(this.getCapital());
    }

    public void removeMember(Player player) {
        if (player == null) return;

        if (members.contains(player.getUniqueId().toString())) {
            members.remove(player.getUniqueId().toString());
        }

        try {
            Town town;
            if (TownyUniverse.getDataSource().getResident(player.getName()).hasTown()) {
                town = TownyUniverse.getDataSource().getResident(player.getName()).getTown();
            } else {
                return;
            }
            if (town.getMayor().equals(TownyUniverse.getDataSource().getResident(player.getName()))) {
                removeTown(town);
                broadcast(ChatColor.GRAY + "The town of " + ChatColor.RED + town.getName() + ChatColor.GRAY + " has left the nation of " + ChatColor.RED + getName());
            }
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }
    }

    public void setMenuDescription(List<String> description) {
        this.menuDescription = description;
    }

    public List<String> getMenuDescription() {
        if (this.menuDescription == null) {
            this.menuDescription = new ArrayList<>();
        }
        return this.menuDescription;
    }

    public boolean doesContainTown(Town town) {
        for (Town t : this.towns) {
            if (town == t) {
                return true;
            }
        }
        return false;
    }

    public void addTown(Town town) {
        if (town == null) return;
        if (doesContainTown(town)) removeTown(town);
        this.towns.add(town);
    }

    public void removeTown(Town town) {
        if (doesContainTown(town))
            this.towns.remove(town);
    }

    public void broadcast(String message) {
        for (String p : this.getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(p)) != null) {
                MessageUtil.sendMsgWithPrefix(Bukkit.getPlayer(UUID.fromString(p)), message);
            }
        }
    }

    public ChatColor getColor() {
        if (this.color == null) {
            this.color = ChatColor.WHITE;
        }
        return this.color;
    }

    public void setColor(ChatColor color) {
        if (color == null) {
            this.color = ChatColor.WHITE;
        } else {
            this.color = color;
        }
    }

    public String getLeaderName() {
        if (this.leaderName.equals(""))
            this.leaderName = "None";
        return this.leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeadershipType() {
        if (this.leadershipType.equals(""))
            this.leadershipType = "None";
        return this.leadershipType;
    }

    public void setLeadershipType(String leadershipType) {
        this.leadershipType = leadershipType;
    }

    public String getCapitalName() {
        if (this.capitalName.equals(""))
            this.capitalName = "None";
        return this.capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public List<Town> getTowns() {
        return this.towns;
    }

}
