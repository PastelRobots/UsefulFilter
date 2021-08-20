package me.pastelrobots.usefulfilter.Listeners;

import me.pastelrobots.usefulfilter.UsefulFilter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class SwearListener implements Listener {
    List<String> sweararray = UsefulFilter.plugin.getConfig().getStringList("badwords");

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (String word : e.getMessage().split(" ")) {
            for(String swear : sweararray) {
                if (UsefulFilter.plugin.getConfig().getStringList("badwords").contains(word)) {
                    e.setMessage(e.getMessage().toLowerCase().replace(swear.toLowerCase(), "****"));
                }
            }
        }
    }
}
