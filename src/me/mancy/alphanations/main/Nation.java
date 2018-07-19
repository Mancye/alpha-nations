package me.mancy.alphanations.main;

import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.managers.NationManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
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

    ItemStack item = new ItemStack(Material.BARRIER);

    public Nation(String name, List<String> members, Location capital) {
        this.name = name;
        if (members == null) members = new ArrayList<>();
        this.members = members;
        this.capital = capital;
    }
    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
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
        this.name = newName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(Player player) {
        if (player == null) return;
        if  (members.contains(player.getUniqueId().toString())) return;
        members.add(player.getUniqueId().toString());
    }

    public void removeMember(Player player) {
        if (player == null) return;
        if (members.contains(player.getUniqueId().toString())) {
            members.remove(player.getUniqueId().toString());
        }
    }

    public void setMenuDescription(List<String> description) {
        this.menuDescription = description;
    }

    public List<String> getMenuDescription() {
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

    public List<Town> getTowns() {
        return this.towns;
    }

}
