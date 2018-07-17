package me.mancy.alphanations.gui;

import me.mancy.alphanations.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class NationSelectionGUI {

    private static void setupNationSelectInv(Inventory inv) {
        InventoryUtil.fillNations(inv, 12);
        InventoryUtil.fillEmptySlots(inv);
    }

    public static Inventory getPlayerNationSelectionInventory() {
        final String invName = ChatColor.RED + "Choose a nation";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        setupNationSelectInv(selectionGUI);
        return selectionGUI;
    }

    public static Inventory getAdminNationSelectionInventory() {
        final String invName = ChatColor.RED + "Choose a nation to edit";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        setupNationSelectInv(selectionGUI);
        return selectionGUI;
    }

    public static Inventory getAdminNationDeleteInventory() {
        final String invName = ChatColor.RED + "Choose a nation to delete";
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
        setupNationSelectInv(selectionGUI);
        return selectionGUI;
    }

}
