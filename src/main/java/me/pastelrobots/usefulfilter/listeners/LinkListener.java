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

public class LinkListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        String playername = event.getPlayer().getName();
        List<String> whitelisted = Config.getStringList("modules.ad-checker.whitelisted-websites");
        Pattern firstPattern = Pattern.compile("[0-9]{1,3}(\\.|d[o0]t|\\(d[o0]t\\)|-|;|:|,|(\\W|\\d|_)*\\s)+[0-9]{1,3}(\\.|d[o0]t|\\(d[o0]t\\)|-|;|:|,|(\\W|\\d|_)*\\s)+[0-9]{1,3}(\\.|d[o0]t|\\(d[0o]t\\)|-|;|:|,|(\\W|\\d|_)*\\s)+[0-9]{1,3}");
        Pattern secondPattern = Pattern.compile("[a-zA-Z0-9\\-\\.]+\\s?(\\.|d[o0]t|\\(d[o0]t\\)|-|;|:|,)\\s?(com|org|net|cz|co|uk|sk|biz|mobi|xxx|eu|me|io|gs|ts|adv|us|de|eu|noip|gs|info|tv|au|pl|cz)");
        Matcher firstMatch = firstPattern.matcher(event.getMessage().toLowerCase().replaceAll("\\s+", ""));
        Matcher secondMatch = secondPattern.matcher(event.getMessage().toLowerCase().replaceAll("\\s+", ""));
        if (Config.getBoolean("modules.ad-checker.ad-checker-enabled") && !player.hasPermission("usefulchat.bypass.ad")) {
            for (String allowed : whitelisted) {
                if (event.getMessage().contains(allowed.toLowerCase()))
                    return;
            }
                if (firstMatch.find() || secondMatch.find()) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.ad-checker.ad-message").replace("%player%", playername)));
                        for (Player staff : Bukkit.getOnlinePlayers()) {
                            if (staff.isOp())
                                staff.sendMessage(ChatColor.RED + playername + " was caught advertising!");
                        }
                        UsefulChat.plugin.getLogger().info(ChatColor.RED + playername + " was caught advertising!");
                    }
                    }
                }
            }
