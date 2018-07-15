package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminNationGUI {



    public static Inventory getAdminEditGUI(Nation nationToEdit) {
        /*
         10           12              14           16
         Edit Block   Edit Name   Edit Color   Delete Nation
         Grass Block   Nametag    White wool   Barrier
         */
        Inventory mainAdminGUI = Bukkit.createInventory(null, 27, ChatColor.RED + ChatColor.ITALIC.toString() + "Editing Nation: " + nationToEdit.getName());

        ItemStack editBlock = new ItemStack(Material.GRASS);
        ItemMeta editBlockMeta = editBlock.getItemMeta();
        editBlockMeta.setDisplayName(ChatColor.GREEN + "Edit block");
        editBlock.setItemMeta(editBlockMeta);
        mainAdminGUI.setItem(10, editBlock);

        ItemStack editName = new ItemStack(Material.NAME_TAG);
        ItemMeta editNameMeta = editBlock.getItemMeta();
        editNameMeta.setDisplayName(ChatColor.GREEN + "Edit name");
        editName.setItemMeta(editNameMeta);
        mainAdminGUI.setItem(12, editName);

        ItemStack editColor = new ItemStack(Material.WOOL);
        ItemMeta editColorMeta = editBlock.getItemMeta();
        editColorMeta.setDisplayName(ChatColor.GREEN + "Edit Color");
        editColor.setItemMeta(editColorMeta);
        mainAdminGUI.setItem(14, editColor);

        ItemStack deleteNation = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = deleteNation.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.RED + "Delete A Nation");
        deleteNation.setItemMeta(deleteMeta);
        mainAdminGUI.setItem(16, deleteNation);

        InventoryUtil.fillEmptySlots(mainAdminGUI);

        return mainAdminGUI;

    }

    public static Inventory getEditBlockInventory() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Set Nation's Block");
        ItemStack defaultBlock = new ItemStack(Material.BOOK);
        inv.setItem(4, defaultBlock);
        InventoryUtil.fillEmptySlots(inv);
        return inv;
    }

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
