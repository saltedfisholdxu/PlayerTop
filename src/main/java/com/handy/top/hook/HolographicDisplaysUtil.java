package com.handy.top.hook;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.handy.lib.core.StrUtil;
import com.handy.lib.util.BaseUtil;
import com.handy.lib.util.ItemStackUtil;
import com.handy.top.PlayerTop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * 全息图工具
 *
 * @author handy
 */
public class HolographicDisplaysUtil {
    private HolographicDisplaysUtil() {
    }

    public static HolographicDisplaysUtil getInstance() {
        return HolographicDisplaysUtil.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HolographicDisplaysUtil INSTANCE = new HolographicDisplaysUtil();
    }

    /**
     * 创建
     *
     * @param textLineList 内容
     * @param location     位置
     */
    public static void create(List<String> textLineList, Location location, String materialName) {
        if (!PlayerTop.USE_HOLOGRAPHIC_DISPLAYS) {
            return;
        }
        Hologram hologram = HologramsAPI.createHologram(PlayerTop.getInstance(), location);
        // 内容
        hologram.clearLines();
        // 第一排的材质
        if (StrUtil.isNotEmpty(materialName)) {
            hologram.appendItemLine(new ItemStack(ItemStackUtil.getMaterial(materialName, Material.NETHERITE_SWORD)));
        }
        for (String textLine : textLineList) {
            hologram.appendTextLine(BaseUtil.replaceChatColor(textLine));
        }
    }

    /**
     * 根据位置删除全息图
     *
     * @param location 位置
     */
    public static void delete(Location location) {
        if (!PlayerTop.USE_HOLOGRAPHIC_DISPLAYS) {
            return;
        }
        // 删除旧的全息图
        for (Hologram hologram : HologramsAPI.getHolograms(PlayerTop.getInstance())) {
            Location hdLocation = hologram.getLocation();
            if (hdLocation.getX() == location.getX() && hdLocation.getY() == location.getY() && hdLocation.getZ() == location.getZ()) {
                hologram.delete();
                return;
            }
        }
    }

}