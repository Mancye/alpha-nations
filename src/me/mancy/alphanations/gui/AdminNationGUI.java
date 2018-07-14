package me.mancy.alphanations.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminNationGUI {

    private static void fillEmptySlots(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName("");
        emptySlot.setItemMeta(emptyMeta);

        for (ItemStack i : inv.getContents()) {
            if (i == null || i.getType().equals(Material.AIR)) {
                i = emptySlot;
            }
        }

    }

    public static Inventory getAdminGUI() {
        /*
         10           12              14           16
         Edit Block   Edit Name   Edit Color   Delete Nation
         Grass Block   Nametag    White wool   Barrier
         */
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + ChatColor.ITALIC.toString() + "Select a nation");



        ItemStack editBlock = new ItemStack(Material.GRASS);
        ItemMeta editBlockMeta = editBlock.getItemMeta();
        editBlockMeta.setDisplayName(ChatColor.GREEN + "Edit block");
        inv.setItem(10, editBlock);

        ItemStack editName = new ItemStack(Material.NAME_TAG);
        ItemMeta editNameMeta = editBlock.getItemMeta();
        editNameMeta.setDisplayName(ChatColor.GREEN + "Edit name");
        inv.setItem(12, editName);

        ItemStack editColor = new ItemStack(Material.WOOL);
        ItemMeta editColorMeta = editBlock.getItemMeta();
        editColorMeta.setDisplayName(ChatColor.GREEN + "Edit Color");
        inv.setItem(14, editBlock);

        ItemStack deleteNation = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = deleteNation.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.RED + "Delete A Nation");
        inv.setItem(16, deleteNation);

        fillEmptySlots(inv);

        return inv;

    }

    public static Inventory getEditBlockInventory() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Set Nation's Block");
        ItemStack defaultBlock = new ItemStack(Material.BOOK);
        inv.setItem(4, defaultBlock);
        fillEmptySlots(inv);
        return inv;
    }

    public static Inventory getEditColorInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Select Nation Color");
        for (int x = 0; x <= 15; x++) {
            ItemStack wool = new ItemStack(Material.WOOL, (byte) x);
            inv.setItem(x, wool);
        }
        fillEmptySlots(inv);
        return inv;

    }

}
