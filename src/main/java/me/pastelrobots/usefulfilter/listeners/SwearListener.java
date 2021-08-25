package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.Config;
import me.pastelrobots.usefulfilter.Utils;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class SwearListener implements Listener {
    List<String> bannedwordsarray = Config.getStringList("badwords");
    @EventHandler
    public void onPlayerSwear(AsyncPlayerChatEvent e) {
        Utils.logInfo("Checking if config has swear checker enabled");
        if(!Config.getBoolean("swear-checker-enabled")) return;
        Utils.logInfo("Checking if player has bypass perms");
        if(e.getPlayer().hasPermission("usefulfilter.bypass")) return;
        Utils.logInfo("Splitting words");
        for (String word : e.getMessage().split(" ")) {
            Utils.logInfo("Checking if words match badwords");
            for(String swear : bannedwordsarray) {
                Utils.logInfo("Checking if words contain swears");
                Utils.logInfo("Checking word similarity");
                    int ratio = FuzzySearch.ratio(word, swear);
                    Utils.logInfo("Checking if ratio meets threshold");
                    Utils.logInfo(String.valueOf(ratio));
                    if (ratio > Config.getInt("ratio-threshold")) {
                        Utils.logInfo("Checking if ratio meets threshold");
                        String string = e.getMessage().replace(word, "****");
                        Utils.logInfo(string);
                        Utils.logInfo("Setting msg");
                        e.setMessage(string);
                        Utils.logInfo(e.getMessage());
                    }
                }
            }
        }
    }
