package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(Config.getBoolean("modules.welcome-message.enabled")) {
            for(String s : Config.getStringList("modules.welcome-message.msg")) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
    }
}
