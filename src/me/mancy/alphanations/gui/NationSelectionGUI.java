package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class NationSelectionGUI {

    public static Inventory getPlayerNationSelectionInventory() {
        final String invName = ChatColor.RED + "Choose a nation";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        InventoryUtil.fillNations(selectionGUI, 12);
        InventoryUtil.fillEmptySlots(selectionGUI);
        return selectionGUI;
    }

    public static Inventory getAdminNationSelectionInventory() {
        final String invName = ChatColor.RED + "Choose a nation to edit";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        InventoryUtil.fillNations(selectionGUI, 12);
        InventoryUtil.fillEmptySlots(selectionGUI);
        return selectionGUI;
    }

    public static Inventory getAdminNationDeleteInventory() {
        final String invName = ChatColor.RED + "Choose a nation to delete";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        InventoryUtil.fillNations(selectionGUI, 12);
        InventoryUtil.fillEmptySlots(selectionGUI);
        return selectionGUI;
    }

}
