package me.mancy.alphanations.main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Nation {
    private Main plugin;

    String name = "";
    List<UUID> members = new ArrayList<>();
    List<String> menuDescription = new ArrayList<>();

    ItemStack item;
    public Nation(Main main) {

    }

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

}
