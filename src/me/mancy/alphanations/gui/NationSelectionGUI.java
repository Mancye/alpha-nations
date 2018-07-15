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

    public static enum NationSelectType {
        PLAYER, ADMIN_NAME, ADMIN_BLOCK, ADMIN_DELETE, ADMIN_COLOR;
    }

    public static Inventory getSelectionInventory(NationSelectType type) {
        String invName = "";
        switch (type) {
            case PLAYER: {
                invName = ChatColor.RED + "Choose A Nation To Join";
                break;
            }
            default: {
                invName = ChatColor.RED + "Choose A Nation To Edit";
                break;
            }
        }
        Inventory selectionGUI = Bukkit.createInventory(null, 27, invName);
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
