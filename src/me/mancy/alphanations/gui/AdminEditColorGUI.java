package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminEditColorGUI {
    public static Inventory getEditColorInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Nation Color");
        for (int x = 0; x <= 15; x++) {
            ItemStack wool = new ItemStack(Material.WOOL, (byte) x);
            inv.setItem(x, wool);
        }
        InventoryUtil.fillEmptySlots(inv);
        return inv;

    }
}
