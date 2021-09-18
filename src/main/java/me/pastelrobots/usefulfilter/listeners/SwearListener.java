package me.pastelrobots.usefulfilter.listeners;

import me.pastelrobots.usefulfilter.utils.Config;
import me.pastelrobots.usefulfilter.utils.Utils;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class SwearListener implements Listener {
    List<String> bannedwordsarray = Config.getStringList("modules.swear-checker.badwords");
    @EventHandler
    public void onPlayerSwear(AsyncPlayerChatEvent e) {
        if(e.getPlayer().hasPermission("usefulfilter.bypass") || e.getPlayer().hasPermission("usefulfilter.bypass.swear")) return;
        Utils.logInfo("Checking if config has swear checker enabled");
        if(!Config.getBoolean("modules.swear-checker.swear-checker-enabled")) return;
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
                    if (ratio > Config.getInt("modules.swear-checker.ratio-threshold")) {
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
