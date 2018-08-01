package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminEditColorGUI {
    public static Inventory getEditColorInventory() {
        final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Nation Color");

        final ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        final ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(" ");
        empty.setItemMeta(emptyMeta);

        InventoryUtil.addButton(inv, Material.RED_WOOL, ChatColor.RED + "Red");
        InventoryUtil.addButton(inv, Material.RED_WOOL, ChatColor.DARK_RED + "Dark Red");

        InventoryUtil.addButton(inv, Material.ORANGE_WOOL, ChatColor.GOLD + "Gold");
        InventoryUtil.addButton(inv, Material.YELLOW_WOOL, ChatColor.YELLOW + "Yellow");

        InventoryUtil.addButton(inv, Material.LIME_WOOL, ChatColor.GREEN + "Green");
        InventoryUtil.addButton(inv, Material.GREEN_WOOL, ChatColor.DARK_GREEN + "Dark Green");

        InventoryUtil.addButton(inv, Material.LIGHT_BLUE_WOOL, ChatColor.AQUA +"Aqua");
        InventoryUtil.addButton(inv, Material.BLUE_WOOL, ChatColor.DARK_AQUA +"Dark Aqua");
        InventoryUtil.addButton(inv, Material.BLUE_WOOL, ChatColor.BLUE + "Blue");
        inv.setItem(9, empty);
        InventoryUtil.addButton(inv, Material.BLUE_WOOL, ChatColor.DARK_BLUE + "Dark Blue");

        InventoryUtil.addButton(inv, Material.MAGENTA_WOOL, ChatColor.LIGHT_PURPLE + "Light Purple");
        InventoryUtil.addButton(inv, Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Dark Purple");

        InventoryUtil.addButton(inv, Material.WHITE_WOOL, ChatColor.WHITE + "White");
        InventoryUtil.addButton(inv, Material.LIGHT_GRAY_WOOL, ChatColor.GRAY + "Light Gray");
        InventoryUtil.addButton(inv, Material.GRAY_WOOL, ChatColor.DARK_GRAY + "Dark Gray");

        InventoryUtil.addButton(inv, Material.BLACK_WOOL, ChatColor.DARK_GRAY + "Black");

        InventoryUtil.fillEmptySlots(inv);
        return inv;

    }
}
