package me.mancy.alphanations.utils;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryUtil {

    public static void fillEmptySlots(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName(" ");
        emptySlot.setItemMeta(emptyMeta);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, emptySlot);
            }
        }
    }

    public static void fillNations(Inventory inv, int startSlot) {
        int slot = startSlot;
        for (Nation nation : NationManager.getNationList()) {
            ItemStack nationItem = nation.getItem();
            if (nation.getItem() == null) nationItem = new ItemStack(Material.BARRIER);
            ItemMeta nationMeta = nationItem.getItemMeta();
            List<String> nationLore = nation.getMenuDescription();
            nationMeta.setDisplayName(nation.getName());
            nationMeta.setLore(nationLore);
            nationItem.setItemMeta(nationMeta);
            inv.setItem(slot, nationItem);
            slot++;
        }
    }

}
