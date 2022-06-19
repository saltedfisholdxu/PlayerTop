package com.handy.top.hook;

import com.handy.lib.core.StrUtil;
import com.handy.playertitle.api.PlayerTitleApi;
import com.handy.top.PlayerTop;

/**
 * 称号util
 *
 * @author handy
 */
public class PlayerTitleUtil {

    private PlayerTitleUtil() {
    }

    public static PlayerTitleUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PlayerTitleUtil INSTANCE = new PlayerTitleUtil();
    }

    /**
     * 查询玩家称号数量
     *
     * @param playerName 玩家名
     * @return 称号数量
     */
    public int getPlayerTitleNumber(String playerName) {
        if (!PlayerTop.USE_TITLE || StrUtil.isEmpty(playerName)) {
            return 0;
        }
        Integer playerTitleNum = PlayerTitleApi.getInstance().getPlayerTitleNum(playerName);
        return playerTitleNum != null ? playerTitleNum : 0;
    }

    /**
     * 查询玩家称号币
     *
     * @param playerName 玩家名
     * @return 称号币数量
     */
    public int getPlayerTitleCoin(String playerName) {
        if (!PlayerTop.USE_TITLE || StrUtil.isEmpty(playerName)) {
            return 0;
        }
        Long playerCoinNum = PlayerTitleApi.getInstance().getPlayerCoinNum(playerName);
        return playerCoinNum != null ? playerCoinNum.intValue() : 0;
    }

}