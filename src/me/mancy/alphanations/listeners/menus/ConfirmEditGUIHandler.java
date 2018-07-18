package me.mancy.alphanations.listeners.menus;

import me.mancy.alphanations.main.Main;
import org.bukkit.event.Listener;

public class ConfirmEditGUIHandler implements Listener {

    public ConfirmEditGUIHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


}
