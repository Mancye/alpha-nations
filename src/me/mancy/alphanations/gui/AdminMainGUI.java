package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminMainGUI {


    public static Inventory getAdminEditGUI(Nation nationToEdit) {
        /*
         10           12              14           16
         Edit Block   Edit Name   Edit Color   Delete Nation
         Grass Block   Nametag    White wool   Barrier
         */
        final Inventory mainAdminGUI = Bukkit.createInventory(null, 27, ChatColor.RED + ChatColor.ITALIC.toString() + "Editing Nation: " + nationToEdit.getName());

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

        ItemStack editColor = new ItemStack(Material.WHITE_WOOL);
        ItemMeta editColorMeta = editBlock.getItemMeta();
        editColorMeta.setDisplayName(ChatColor.GREEN + "Edit Color");
        editColor.setItemMeta(editColorMeta);
        mainAdminGUI.setItem(14, editColor);

        ItemStack deleteNation = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = deleteNation.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.RED + "Delete This Nation");
        deleteNation.setItemMeta(deleteMeta);
        mainAdminGUI.setItem(16, deleteNation);

        InventoryUtil.fillEmptySlots(mainAdminGUI);

        return mainAdminGUI;

    }





}
