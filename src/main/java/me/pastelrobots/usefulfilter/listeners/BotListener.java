package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.utils.Arrays;
import me.pastelrobots.usefulfilter.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BotListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.bot")) return;
        if(!Config.getBoolean("modules.anti-bot.enabled")) return;
        if(Config.getBoolean("modules.anti-bot.mute-messages-until-moved")) {
            Arrays.AntiBot.add(p.getUniqueId());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.anti-bot.join-message")));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.bot")) return;
        if(!Config.getBoolean("modules.anti-bot.enabled")) return;
        if(Config.getBoolean("modules.anti-bot.mute-messages-until-moved")) {
            Arrays.AntiBot.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.bot")) return;
        if(!Config.getBoolean("modules.anti-bot.enabled")) return;
        if(Config.getBoolean("modules.anti-bot.mute-messages-until-moved") && Arrays.AntiBot.contains(p.getUniqueId())) {
            Arrays.AntiBot.remove(p.getUniqueId());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.anti-bot.success-message")));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.bot")) return;
        if(!Config.getBoolean("modules.anti-bot.enabled")) return;
        if(Config.getBoolean("modules.anti-bot.mute-messages-until-moved") && Arrays.AntiBot.contains(p.getUniqueId())) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.anti-bot.tried-to-chat-message")));
        }
    }
}
