package me.mancy.alphanations.utils;

import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class InventoryUtil {

    public static void fillEmptySlotsNationSelect(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.GREEN + "Stay Tuned");
        emptySlot.setItemMeta(emptyMeta);

        ItemStack none = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta noneMeta = none.getItemMeta();
        noneMeta.setDisplayName(" ");
        none.setItemMeta(noneMeta);

        Integer[] slots = {10, 12, 14, 16, 37, 39, 41, 43};
        for (int i = 0; i < inv.getSize(); i++) {
            if (Arrays.asList(slots).contains(i)) {
                if (inv.getItem(i) == null)
                inv.setItem(i, emptySlot);
            } else {
                inv.setItem(i, none);
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
            ItemStack nationItem = nation.getItem();
            if (nation.getItem() == null) nationItem = new ItemStack(Material.BARRIER);
            ItemMeta nationMeta = nationItem.getItemMeta();
            List<String> nationLore = nation.getMenuDescription();
            nationMeta.setDisplayName(nation.getColor() + nation.getName());
            nationMeta.setLore(nationLore);
            nationItem.setItemMeta(nationMeta);
            inv.setItem(slot, nationItem);
            if (inv.getSize() == 27) {
                    inv.setItem(slot, nationItem);
                    slot += 2;
            } else if (inv.getSize() == 54) {
                if (slot == 16) {
                    slot += 21;
                } else {
                    slot += 2;
                }
            }
        }
    }

    public static void addButton(Inventory inv, Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        inv.addItem(item);
    }

    public static void addButton(Inventory inv, Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        inv.addItem(item);
    }

    public static void addButton(Inventory inv, int slot, Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        inv.setItem(slot, item);
    }

    public static void addButton(Inventory inv, int slot, Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        inv.setItem(slot, item);
    }

    public static void addButton(Inventory inv, int slot, ItemStack itemStack, String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        inv.setItem(slot, itemStack);
    }

}
