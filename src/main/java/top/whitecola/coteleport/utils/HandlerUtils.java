package top.whitecola.coteleport.utils;

import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.handler.PlayerBackHandler;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;

public class HandlerUtils {


    public static <T> T getHandler(Class<T> handler){

        if(handler.getClass().equals(PlayerTeleportEventHandler.class))
            return (T) CoTeleport.instance.teleportEventHandler;


        if(handler.getClass().equals(PlayerBackHandler.class)){
            return (T) CoTeleport.instance.getPlayerBackHandler();
        }

        return null;
    }

}
