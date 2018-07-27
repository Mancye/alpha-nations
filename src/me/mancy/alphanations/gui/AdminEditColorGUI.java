package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class AdminEditColorGUI {
    public static Inventory getEditColorInventory() {
        final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Nation Color");

        DyeColor[] colors = {DyeColor.WHITE, DyeColor.BLACK, DyeColor.BLUE, DyeColor.BROWN, DyeColor.CYAN, DyeColor.GRAY, DyeColor.GREEN, DyeColor.LIGHT_BLUE,
                    DyeColor.LIGHT_GRAY, DyeColor.LIME, DyeColor.MAGENTA, DyeColor.ORANGE, DyeColor.PINK, DyeColor.PURPLE, DyeColor.RED, DyeColor.YELLOW};
        for (int x = 0; x < colors.length; x++) {
            Wool wool = new Wool(colors[x]);
            ItemStack woolItem = wool.toItemStack(1);
            ItemMeta woolMeta = woolItem.getItemMeta();
            woolMeta.setDisplayName(colors[x].name());
            woolItem.setItemMeta(woolMeta);
            inv.setItem(x, woolItem);
        }

        InventoryUtil.fillEmptySlots(inv);
        return inv;

    }
}
