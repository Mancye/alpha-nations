package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmEditGUI {


    public static Inventory getConfirmMenu() {
        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.RED + "Are you sure?");

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Yes");
        confirm.setItemMeta(confirmMeta);
        menu.setItem(11, confirm);

        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "No");
        cancel.setItemMeta(cancelMeta);
        menu.setItem(15, cancel);

        InventoryUtil.fillEmptySlots(menu);
        return menu;
    }


}
