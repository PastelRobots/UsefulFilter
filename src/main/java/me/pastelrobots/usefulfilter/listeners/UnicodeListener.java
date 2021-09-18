package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.UsefulChat;
import me.pastelrobots.usefulfilter.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        String message = event.getMessage();
        List<String> whitelisted = Config.getStringList("modules.unicode-checker.whitelist");
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-~!@#$%^&*()<>_+=-{}|';:.,\\[\"\"]|';:.,/?><_.]+$");
        Matcher matcher = pattern.matcher(event.getMessage().toLowerCase().replaceAll("\\s+", ""));
        if (Config.getBoolean("modules.unicode-checker.enabled") && !player.hasPermission("usefulchat.bypass.unicode")) {
            for (String allowed : whitelisted) {
                if (event.getMessage().contains(allowed))
                    return;
            }
            if (!matcher.find()) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.unicode-checker.message").replace("%player%", player.getName())));
                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.isOp())
                        staff.sendMessage(ChatColor.RED + event.getPlayer().getName() + " was caught using unicode!");
                }
                UsefulChat.plugin.getLogger().info(ChatColor.RED + event.getPlayer().getName() + " was caught using unicode!");
                }
        }
    }
}

