package me.mancy.alphanations.gui;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class NationSelectionGUI {

    public static Inventory getSelectionInventory() {

        Inventory selectionGUI = Bukkit.createInventory(null, 27, ChatColor.RED + "Choose A Nation");
        int slot = 12;
        for (Nation nation : NationManager.getNationList()) {
            ItemStack nationItem = nation.getItem();
            if (nation.getItem() == null) nationItem = new ItemStack(Material.BARRIER);
            ItemMeta nationMeta = nationItem.getItemMeta();
            List<String> nationLore = nation.getMenuDescription();
            nationMeta.setDisplayName(nation.getName());
            nationMeta.setLore(nationLore);
            nationItem.setItemMeta(nationMeta);
            selectionGUI.setItem(slot, nationItem);
            slot++;
        }

        return selectionGUI;
    }

}
