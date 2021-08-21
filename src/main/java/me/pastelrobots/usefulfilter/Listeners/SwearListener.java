package me.pastelrobots.usefulfilter.Listeners;

import me.pastelrobots.usefulfilter.UsefulFilter;
import me.pastelrobots.usefulfilter.Utils;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class SwearListener implements Listener {
    List<String> bannedwordsarray = UsefulFilter.plugin.getConfig().getStringList("badwords");
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
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
                    if (ratio > UsefulFilter.plugin.getConfig().getInt("ratio-threshold")) {
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

    public static int getStringSimilarity(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int n = s1.length();
        int m = s2.length();

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
            n = m;
            m = s2.length();
        }

        int p[] = new int[n + 1];
        int d[] = new int[n + 1];
        int _d[];

        int i;
        int j;

        char t_j;

        int cost;

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = s2.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s1.charAt(i - 1) == t_j ? 0 : 1;
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            _d = p;
            p = d;
            d = _d;
        }

        return p[n];
    }
    }
