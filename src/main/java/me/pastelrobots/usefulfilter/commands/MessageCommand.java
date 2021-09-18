package me.pastelrobots.usefulfilter.commands;

import me.pastelrobots.usefulfilter.utils.Config;
import me.pastelrobots.usefulfilter.UsefulChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if(s instanceof Player p) {

            StringBuilder message = new StringBuilder();
            for(int i = 1; i != args.length; i++) {
                assert false;
                message.append(args[i]).append(" ");
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(args.length <= 1) {
                p.sendMessage(ChatColor.RED + "Invalid Arguments!");
                return true;
            }
            if(!Config.getBoolean("modules.message-format.enabled")) {
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o%player% whispers to you: %message%").replace("%player%", p.getName()).replace("%message%", message.toString()));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oYou whisper to %player%: %message%").replace("%player%", target.getName()).replace("%message%", message.toString()));
            }
            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.invalid-msg").replace("%target%", args[0])));
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.send-format").replace("%target%", args[0]).replace("%message%", message.toString())));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.receive-format").replace("%player%", p.getName()).replace("%message%", message.toString())));
            return true;

        } else {
            StringBuilder message = new StringBuilder();
            for(int i = 1; i != args.length; i++) {
                assert false;
                message.append(args[i]).append(" ");
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(args.length <= 1) {
                UsefulChat.plugin.getLogger().info(ChatColor.RED + "Invalid Arguments!");
                return true;
            }
            if(!Config.getBoolean("modules.message-format.enabled")) {
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o%player% whispers to you: %message%").replace("%player%", "CONSOLE").replace("%message%", message.toString()));
                UsefulChat.plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&7&oYou whisper to %player%: %message%").replace("%player%", target.getName()).replace("%message%", message.toString()));
            }
            if (target == null || !target.isOnline()) {
                UsefulChat.plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.invalid-msg").replace("%target%", args[0])));
                return true;
            }
            UsefulChat.plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.send-format").replace("%target%", args[0]).replace("%message%", message.toString())));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getString("modules.message-format.receive-format").replace("%player%", "CONSOLE").replace("%message%", message.toString())));
            return true;
        }
    }
}
