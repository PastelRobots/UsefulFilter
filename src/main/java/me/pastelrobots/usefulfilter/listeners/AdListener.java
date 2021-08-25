package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.Config;
import me.pastelrobots.usefulfilter.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdListener implements Listener {
    List<String> whitelistarray = Config.getStringList("whitelisted-websites");
    final Pattern p = Pattern.compile("((www\\.)?+[a-zA-Z0-9\\.\\-\\_]+(\\.[a-zA-Z]{2,3})+)|(\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b)");
    @EventHandler
    public void onPlayerAd(AsyncPlayerChatEvent e) {
        Utils.logInfo("Checking if config has ad checker enabled");
        if(!Config.getBoolean("ad-checker-enabled")) return;
        Utils.logInfo("Checking if player has bypass perms");
        if(e.getPlayer().hasPermission("usefulfilter.bypass")) return;
        Utils.logInfo("Grabbing Msg");
        String msg = e.getMessage();
        Utils.logInfo("Checking if msg matches regex");
        Utils.logInfo("Grabbing the ip that was sent");
        Matcher m = p.matcher(msg);
        if(msg.contains("http://") || msg.contains("https://")) {

            if(m.find()) {
                String address = m.group(0);
                for (String ip : whitelistarray) {
                    Utils.logInfo("Checking if the ip is whitelisted");
                    if (Config.getStringList("whitelisted-websites").contains(address)) {
                        Utils.logInfo(ChatColor.YELLOW + "IP Sent is whitelisted");
                    } else {
                        String string = e.getMessage().replace(address, "***.***.*.*");
                        Utils.logInfo(string);
                        Utils.logInfo("Setting msg");
                        e.setMessage(string);
                        Utils.logInfo(e.getMessage());
                    }
                }
            }
        } else if(!msg.contains("http://") || !msg.contains("https://")) {

            if(m.find()) {
            String address = m.group(0);
            for (String ip : whitelistarray) {
                Utils.logInfo("Checking if the ip is whitelisted");
                if (Config.getStringList("whitelisted-websites").contains(address)) {
                    Utils.logInfo(ChatColor.YELLOW + "IP Sent is whitelisted");
                } else {
                    String string = e.getMessage().replace(address, "***.***.*.*");
                    Utils.logInfo(string);
                    Utils.logInfo("Setting msg");
                    e.setMessage(string);
                    Utils.logInfo(e.getMessage());
                }
            }
        }
        }
    }
}
