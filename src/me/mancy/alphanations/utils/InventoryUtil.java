package me.mancy.alphanations.utils;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryUtil {

    public static void fillEmptySlotsNationSelect(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.GREEN + "Stay Tuned");
        emptySlot.setItemMeta(emptyMeta);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, emptySlot);
            }
        }
    }
    public static void fillEmptySlots(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.GREEN + " ");
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
            if (slot > inv.getSize() && slot < 54) {
                Map<ItemStack, Integer> previousInv = new HashMap<>();
                for (int x = 0; x < inv.getSize() - 1; x++) {
                    if (inv.getItem(x) != null && !inv.getItem(x).getType().equals(Material.AIR))
                        previousInv.put(inv.getItem(x), x);
                }
                Inventory resizedInv = Bukkit.createInventory(inv.getHolder(), 54, inv.getTitle());
                for (ItemStack i : previousInv.keySet()) {
                    resizedInv.setItem(previousInv.get(i), i);
                }
                inv = resizedInv;
            } else {
                return;
            }
            ItemStack nationItem = nation.getItem();
            if (nation.getItem() == null) nationItem = new ItemStack(Material.BARRIER);
            ItemMeta nationMeta = nationItem.getItemMeta();
            List<String> nationLore = nation.getMenuDescription();
            nationMeta.setDisplayName(nation.getColor() + nation.getName());
            nationMeta.setLore(nationLore);
            nationItem.setItemMeta(nationMeta);
            inv.setItem(slot, nationItem);
            slot += 2;
        }
    }

    public static void addButton(Inventory inv, Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        inv.addItem(item);
    }

}
