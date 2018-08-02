package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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
        List<String> blockLore = new ArrayList<>();
        blockLore.add(ChatColor.GRAY + "Edit the block which will represent");
        blockLore.add(ChatColor.GRAY + "the nation during selection.");
        editBlockMeta.setLore(blockLore);
        editBlock.setItemMeta(editBlockMeta);
        mainAdminGUI.setItem(10, editBlock);

        ItemStack editName = new ItemStack(Material.NAME_TAG);
        ItemMeta editNameMeta = editBlock.getItemMeta();
        editNameMeta.setDisplayName(ChatColor.YELLOW + "Edit name");
        List<String> nameLore = new ArrayList<>();
        nameLore.add(ChatColor.GRAY + "Change the name of the nation.");
        editNameMeta.setLore(nameLore);
        editName.setItemMeta(editNameMeta);
        mainAdminGUI.setItem(12, editName);

        ItemStack editColor = new ItemStack(Material.WHITE_WOOL);
        ItemMeta editColorMeta = editBlock.getItemMeta();
        editColorMeta.setDisplayName(ChatColor.DARK_AQUA + "Edit color");
        List<String> colorLore = new ArrayList<>();
        colorLore.add(ChatColor.GRAY + "Set the nation's main color.");
        editColorMeta.setLore(colorLore);
        editColor.setItemMeta(editColorMeta);
        mainAdminGUI.setItem(14, editColor);

        ItemStack deleteNation = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = deleteNation.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.RED + "Delete nation");
        List<String> deleteLore = new ArrayList<>();
        deleteLore.add(ChatColor.GRAY + "Delete this nation.");
        deleteMeta.setLore(deleteLore);
        deleteNation.setItemMeta(deleteMeta);
        mainAdminGUI.setItem(16, deleteNation);

        InventoryUtil.fillEmptySlots(mainAdminGUI);

        return mainAdminGUI;

    }





}
