package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class NationSelectionGUI {

    private static Inventory selectionGUI;

    public static Inventory getSelectionInventory() {

        selectionGUI = Bukkit.createInventory(null, 27, ChatColor.RED + "Choose A Nation");

        for (Nation nation : NationManager.getNationList()) {
            ItemStack nationItem = new ItemStack(Material.BOOK);
            ItemMeta nationMeta = nationItem.getItemMeta();
            List<String> nationLore = nation.getMenuDescription();
            nationMeta.setDisplayName(nation.getName());
        }

        return selectionGUI;
    }

}
