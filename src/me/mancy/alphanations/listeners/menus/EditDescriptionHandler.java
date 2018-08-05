package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.gui.AdminMainGUI;
import me.mancy.alphanations.gui.ConfirmEditGUI;
import me.mancy.alphanations.main.Main;
import me.mancy.alphanations.main.Nation;
import me.mancy.alphanations.managers.NationEditorManager;
import me.mancy.alphanations.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.HashMap;
import java.util.Map;

public class EditDescriptionHandler implements Listener {

    private final Map<Player, Nation> playersEditingDesc = new HashMap<>();

    public EditDescriptionHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }
    @EventHandler
    private void handleClicks(InventoryClickEvent event) {
        if (!(event.getInventory().getName().contains("Editing description for "))) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if ((NationEditorManager.getPlayersNation((Player) event.getWhoClicked()) == null)) return;

        Nation nation = NationEditorManager.getPlayersNation((Player) event.getWhoClicked());
        Player p = (Player) event.getWhoClicked();
        switch (event.getSlot()) {
            case 11: p.closeInventory();
                playersEditingDesc.put(p, nation);
                MessageUtil.sendMsgWithPrefix(p, ChatColor.GRAY + "Enter a line:");
                break;
            case 13: if (nation.getMenuDescription().size() >= 1)
                NationEditorManager.descriptionsToClear.add(nation);
                NationEditorManager.playersEditType.put(p, "DESCRIPTION_CLEAR");
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                break;
            case 15: if (nation.getMenuDescription().size() >= 1)
                NationEditorManager.descriptionChanges.put(nation, nation.getMenuDescription().get(nation.getMenuDescription().size() - 1));
                NationEditorManager.playersEditType.put(p, "DESCRIPTION_REMOVE");
                p.openInventory(ConfirmEditGUI.getConfirmMenu());
                break;
            case 18: event.getWhoClicked().openInventory(AdminMainGUI.getAdminEditGUIPageTwo(nation));
            break;
        }

    event.setCancelled(true);
    }

    @EventHandler
    private void handleEditDescInput(AsyncPlayerChatEvent event) {
        if (!playersEditingDesc.containsKey(event.getPlayer())) return;
        Nation nation = playersEditingDesc.get(event.getPlayer());
        String descLine = ChatColor.stripColor(event.getMessage());
        NationEditorManager.descriptionChanges.put(nation, descLine);
        NationEditorManager.playersEditType.put(event.getPlayer(), "DESCRIPTION_ADD");
        event.getPlayer().openInventory(ConfirmEditGUI.getConfirmMenu());
        playersEditingDesc.remove(event.getPlayer());
        event.setCancelled(true);
    }

}
