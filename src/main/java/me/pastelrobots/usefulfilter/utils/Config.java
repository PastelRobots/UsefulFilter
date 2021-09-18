package me.pastelrobots.usefulfilter.utils;

import me.pastelrobots.usefulfilter.UsefulChat;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Config {

    public static int getInt(String intpath) {
        return UsefulChat.plugin.getConfig().getInt(intpath);
    }

    public static boolean getBoolean(String booleanpath) {
        return UsefulChat.plugin.getConfig().getBoolean(booleanpath);
    }

    public static double getDouble(String doublepath) {
        return UsefulChat.plugin.getConfig().getDouble(doublepath);
    }

    public static String getString(String stringpath) {
        return UsefulChat.plugin.getConfig().getString(stringpath);
    }

    public static Object getObject(String objectpath, Class<Boolean> clazz) {
        return UsefulChat.plugin.getConfig().getObject(objectpath, clazz);
    }

    public static List<Boolean> getBooleanList(String booleanpath) {
        return UsefulChat.plugin.getConfig().getBooleanList(booleanpath);
    }

    public static List<String> getStringList(String stringpath) {
        return UsefulChat.plugin.getConfig().getStringList(stringpath);
    }

    public static List<Byte> getByteList(String bytepath) {
       return UsefulChat.plugin.getConfig().getByteList(bytepath);
    }

    public static List<Character> getCharacterList(String characterpath) {
       return UsefulChat.plugin.getConfig().getCharacterList(characterpath);
    }

    public static Color getColour(String colourpath) {
        return UsefulChat.plugin.getConfig().getColor(colourpath);
    }

    public static List<Double> getDoubleList(String doublepath) {
        return UsefulChat.plugin.getConfig().getDoubleList(doublepath);
    }

    public static List<Float> getFloatList(String floatpath) {
        return UsefulChat.plugin.getConfig().getFloatList(floatpath);
    }

    public static List<Integer> getIntList(String intpath) {
        return UsefulChat.plugin.getConfig().getIntegerList(intpath);
    }

    public static ItemStack getItemStack(String itemstackpath) {
        return UsefulChat.plugin.getConfig().getItemStack(itemstackpath);
    }

    public static List getList(String listpath) {
        return UsefulChat.plugin.getConfig().getList(listpath);
    }

    public static Vector getVector(String vecpath) {
       return UsefulChat.plugin.getConfig().getVector(vecpath);
    }

    public static Set<String> getKeys(boolean deep) {
        return UsefulChat.plugin.getConfig().getKeys(deep);
    }

    public static Map<String, Object> getValues(boolean deep) {
       return UsefulChat.plugin.getConfig().getValues(deep);
    }

    public static List<Short> getShortList(String shortpath) {
       return UsefulChat.plugin.getConfig().getShortList(shortpath);
    }

    public static OfflinePlayer getOfflinePlayer(String offlpath) {
       return UsefulChat.plugin.getConfig().getOfflinePlayer(offlpath);
    }

    public static List<Map<?, ?>> getMapList(String mappath) {
        return UsefulChat.plugin.getConfig().getMapList(mappath);
    }

    public static List<Long> getLongList(String longpath) {
       return UsefulChat.plugin.getConfig().getLongList(longpath);
    }

    public static Long getLong(String longpath) {
       return UsefulChat.plugin.getConfig().getLong(longpath);
    }

    public static Location getLocation(String locpath) {
       return UsefulChat.plugin.getConfig().getLocation(locpath);
    }

    public static ConfigurationSection getConfigSection(String cspath) {
        return UsefulChat.plugin.getConfig().getConfigurationSection(cspath);
    }

}
