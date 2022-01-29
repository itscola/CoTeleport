package top.whitecola.coteleport.utils;

import org.bukkit.OfflinePlayer;

public class PlayerUtils {
    public static boolean isSamePlayer(OfflinePlayer player, OfflinePlayer player2){
        if(player==null || player2==null){
            return false;
        }
        if(player.getUniqueId().toString().equals(player2.getUniqueId().toString())){
            return true;
        }

        return false;
    }
}
