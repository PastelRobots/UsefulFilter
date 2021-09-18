package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.utils.Config;
import me.pastelrobots.usefulfilter.UsefulChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class SpamListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("usefulfilter.bypass") || player.hasPermission("usefulfilter.bypass.spam")) return;
        if(!Config.getBoolean("modules.spam-checker.spam-checker-enabled")) return;
        String message = event.getMessage();
        HashMap<Player, String> previousMessages = new HashMap<>();
        ArrayList<Player> cooldown = new ArrayList<>();

        if (cooldown.contains(player)) {
            player.sendMessage(ChatColor.RED + "You are talking too fast! Please wait before sending your next massage!");
            event.setCancelled(true);
            return;
        }

        if (previousMessages.containsKey(player))
            if (message.equalsIgnoreCase(previousMessages.get(player))) {
                player.sendMessage(ChatColor.RED + "Spamming is not allowed on this server!");
                event.setCancelled(true);
            }

        previousMessages.put(player, message);
        cooldown.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(player);
            }
        }.runTaskLater(UsefulChat.plugin, Config.getInt("modules.spam-checker.cooldown"));
    }
}
