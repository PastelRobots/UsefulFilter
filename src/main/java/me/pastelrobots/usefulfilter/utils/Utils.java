package me.pastelrobots.usefulfilter.utils;

import me.pastelrobots.usefulfilter.UsefulChat;

public final class Utils {

    public static void logInfo(String message) { // Temporarily Disabled!
        if(Config.getBoolean("console.debug-mode")) {
            UsefulChat.plugin.getLogger().info(message);
        }
    }
}
