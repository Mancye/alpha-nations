package me.mancy.alphanations.main;

import com.palmergames.bukkit.towny.object.Town;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Nation {
    private Main plugin;

    private String name = "";
    private List<UUID> members = new ArrayList<>();
    private List<String> menuDescription = new ArrayList<>();
    private List<Town> towns = new ArrayList<>();

    ItemStack item = new ItemStack(Material.BARRIER);

    public Nation(String name, List<UUID> members) {
        this.name = name;
        if (members == null) members = new ArrayList<>();
        this.members = members;
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

    public void setName(String newName) {
        this.name = newName;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public void addMember(Player player) {
        if (player == null) return;
        if  (members.contains(player.getUniqueId())) return;
        members.add(player.getUniqueId());
    }

    public void removeMember(Player player) {
        if (player == null) return;
        if (members.contains(player.getUniqueId())) {
            members.remove(player);
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
        for (UUID uuid : this.getMembers()) {
            if (Bukkit.getPlayer(uuid) != null) {
                MessageUtil.sendMsgWithPrefix(Bukkit.getPlayer(uuid), message);
            }
        }
    }

    public List<Town> getTowns() {
        return this.towns;
    }
}
