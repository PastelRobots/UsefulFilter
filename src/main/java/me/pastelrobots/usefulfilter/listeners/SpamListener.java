package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.UsefulChat;
import me.pastelrobots.usefulfilter.utils.Arrays;
import me.pastelrobots.usefulfilter.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpamListener implements Listener {
    long time = Config.getLong("modules.spam-checker.cooldown") * 1000;
    boolean muted = false;

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void OnQuit(PlayerQuitEvent e) {
        Arrays.msgdelay.remove(e.getPlayer().getUniqueId());
        Arrays.cmddelay.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFastChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Arrays.cmddelay.containsKey(p.getUniqueId()) && System.currentTimeMillis() >= Arrays.cmddelay.get(p.getUniqueId()))
            Arrays.cmddelay.remove(p.getUniqueId());
        if (Arrays.cmddelay.containsKey(p.getUniqueId())) {
            e.setCancelled(true);
            long ms = Arrays.cmddelay.get(p.getUniqueId()) - System.currentTimeMillis();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.spam-checker.message").replace("%secs%", String.valueOf(ms / 1000)).replace("%player%", p.getName())));
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.isOp())
                    staff.sendMessage(ChatColor.RED + p.getName() + " was caught spamming!");
            }
            UsefulChat.plugin.getLogger().info(ChatColor.RED + p.getName() + " was caught spamming!");
        } else if(!p.hasPermission("usefulchat.bypass.spam") && !Arrays.AntiBot.contains(p.getUniqueId())) {
            Arrays.cmddelay.put(p.getUniqueId(), System.currentTimeMillis() + this.time);
        }


    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFastCmd(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (Arrays.msgdelay.containsKey(p.getUniqueId()) && System.currentTimeMillis() >= Arrays.msgdelay.get(p.getUniqueId()))
            Arrays.msgdelay.remove(p.getUniqueId());
        if (Arrays.msgdelay.containsKey(p.getUniqueId())) {
            e.setCancelled(true);
            long ms = Arrays.msgdelay.get(p.getUniqueId()) - System.currentTimeMillis();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.spam-checker.message").replace("%secs%", String.valueOf(ms / 1000)).replace("%player%", p.getName())));
        } else if(!p.hasPermission("usefulchat.bypass.spam")) {
            Arrays.msgdelay.put(p.getUniqueId(), System.currentTimeMillis() + this.time);
        }
    }
}
