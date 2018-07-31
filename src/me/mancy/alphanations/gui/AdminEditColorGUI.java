package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class AdminEditColorGUI {
    public static Inventory getEditColorInventory() {
        final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Nation Color");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.RED + "Red");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_RED + "Dark Red");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.GOLD + "Gold");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.YELLOW + "Yellow");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.GREEN + "Green");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_GREEN + "Dark Green");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.AQUA +"Aqua");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_AQUA +"Dark Aqua");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.BLUE + "Blue");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_BLUE + "Dark Blue");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.LIGHT_PURPLE + "Light Purple");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_PURPLE + "Dark Purple");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.WHITE + "White");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.GRAY + "Light Gray");
        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_GRAY + "Dark Gray");

        InventoryUtil.addButton(inv, Material.WOOL, ChatColor.DARK_GRAY + "Black");

        InventoryUtil.fillEmptySlots(inv);
        return inv;

    }
}
