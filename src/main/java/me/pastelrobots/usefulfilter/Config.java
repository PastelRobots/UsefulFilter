package me.pastelrobots.usefulfilter;

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
        return UsefulFilter.plugin.getConfig().getInt(intpath);
    }

    public static boolean getBoolean(String booleanpath) {
        return UsefulFilter.plugin.getConfig().getBoolean(booleanpath);
    }

    public static double getDouble(String doublepath) {
        return UsefulFilter.plugin.getConfig().getDouble(doublepath);
    }

    public static String getString(String stringpath) {
        return UsefulFilter.plugin.getConfig().getString(stringpath);
    }

    public static Object getObject(String objectpath, Class<Boolean> clazz) {
        return UsefulFilter.plugin.getConfig().getObject(objectpath, clazz);
    }

    public static List<Boolean> getBooleanList(String booleanpath) {
        return UsefulFilter.plugin.getConfig().getBooleanList(booleanpath);
    }

    public static List<String> getStringList(String stringpath) {
        return UsefulFilter.plugin.getConfig().getStringList(stringpath);
    }

    public static List<Byte> getByteList(String bytepath) {
       return UsefulFilter.plugin.getConfig().getByteList(bytepath);
    }

    public static List<Character> getCharacterList(String characterpath) {
       return UsefulFilter.plugin.getConfig().getCharacterList(characterpath);
    }

    public static Color getColour(String colourpath) {
        return UsefulFilter.plugin.getConfig().getColor(colourpath);
    }

    public static List<Double> getDoubleList(String doublepath) {
        return UsefulFilter.plugin.getConfig().getDoubleList(doublepath);
    }

    public static List<Float> getFloatList(String floatpath) {
        return UsefulFilter.plugin.getConfig().getFloatList(floatpath);
    }

    public static List<Integer> getIntList(String intpath) {
        return UsefulFilter.plugin.getConfig().getIntegerList(intpath);
    }

    public static ItemStack getItemStack(String itemstackpath) {
        return UsefulFilter.plugin.getConfig().getItemStack(itemstackpath);
    }

    public static List getList(String listpath) {
        return UsefulFilter.plugin.getConfig().getList(listpath);
    }

    public static Vector getVector(String vecpath) {
       return UsefulFilter.plugin.getConfig().getVector(vecpath);
    }

    public static Set<String> getKeys(boolean deep) {
        return UsefulFilter.plugin.getConfig().getKeys(deep);
    }

    public static Map<String, Object> getValues(boolean deep) {
       return UsefulFilter.plugin.getConfig().getValues(deep);
    }

    public static List<Short> getShortList(String shortpath) {
       return UsefulFilter.plugin.getConfig().getShortList(shortpath);
    }

    public static OfflinePlayer getOfflinePlayer(String offlpath) {
       return UsefulFilter.plugin.getConfig().getOfflinePlayer(offlpath);
    }

    public static List<Map<?, ?>> getMapList(String mappath) {
        return UsefulFilter.plugin.getConfig().getMapList(mappath);
    }

    public static List<Long> getLongList(String longpath) {
       return UsefulFilter.plugin.getConfig().getLongList(longpath);
    }

    public static Long getLong(String longpath) {
       return UsefulFilter.plugin.getConfig().getLong(longpath);
    }

    public static Location getLocation(String locpath) {
       return UsefulFilter.plugin.getConfig().getLocation(locpath);
    }

    public static ConfigurationSection getConfigSection(String cspath) {
        return UsefulFilter.plugin.getConfig().getConfigurationSection(cspath);
    }

}
