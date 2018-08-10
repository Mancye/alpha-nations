package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class AdminEditDescriptionGUI {

    public static Inventory getDescMenu(Nation nation) {
        Inventory inv = Bukkit.createInventory(null,27, ChatColor.GRAY + "Editing description for " + nation.getName());
        InventoryUtil.addButton(inv, 11, Material.LIME_WOOL, ChatColor.GREEN + "Add a line");
        InventoryUtil.addButton(inv, 13, Material.BARRIER, ChatColor.DARK_RED + "Clear contents");
        InventoryUtil.addButton(inv, 15, Material.RED_WOOL, ChatColor.RED + "Remove last line");
        InventoryUtil.addButton(inv, 18, Material.ARROW, ChatColor.RED + "Back");
        InventoryUtil.addButton(inv, 26, Material.ARROW, ChatColor.AQUA + "Current Description:", nation.getMenuDescription());
        InventoryUtil.fillEmptySlots(inv);
        return inv;
    }

}
