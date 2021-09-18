package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.utils.Config;
import me.pastelrobots.usefulfilter.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CapsListener implements Listener {
    @EventHandler
    public void onPlayerCaps(AsyncPlayerChatEvent e) {

        Utils.logInfo("Checking if player has bypass perms");
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.caps")) return;
        Utils.logInfo("Checking if config has caps checker enabled");
        if(!Config.getBoolean("modules.caps-checker.caps-checker-enabled")) return;
        Utils.logInfo("Grabbing Msg");
        String msg = e.getMessage();

        Utils.logInfo("Checking caps amount");
        int capspercent = 0;
        for(char c : msg.toCharArray()) {
            if(Character.isUpperCase(c)) {
                ++capspercent;
            }
        }
        Utils.logInfo("Getting caps percentage");
        double pct = (capspercent * 1D) / (msg.length() * 1D) * 100D;
        Utils.logInfo("Checking caps percentage");
        if(pct > Config.getDouble("modules.caps-checker.caps-checker-percentage")) {
            Utils.logInfo("Changing message to lowercase");
            String msgLower = e.getMessage().toLowerCase();
            Utils.logInfo("Changing msg");
            e.setMessage(msgLower);
            e.getPlayer().sendMessage(ChatColor.RED + "Watch the caps!");
        }

    }
}
