package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminEditBlockGUI {
    public static Inventory getEditBlockInventory() {
        final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Set Nation's Block");
        ItemStack defaultBlock = new ItemStack(Material.BOOK);
        inv.setItem(4, defaultBlock);
        InventoryUtil.addButton(inv, 0, Material.ARROW, ChatColor.RED + "Back");
        InventoryUtil.fillEmptySlots(inv);
        return inv;
    }
}
