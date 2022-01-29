package top.whitecola.coteleport.utils;

import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.handler.EventHandlerAdapter;
import top.whitecola.coteleport.handler.PlayerBackHandler;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;

public class HandlerFactory {

    public static <T extends EventHandlerAdapter> T getHandler(Class<T> handler){



        if(handler.getSimpleName().equals(PlayerTeleportEventHandler.class.getSimpleName()))
            return (T) CoTeleport.instance.getTeleportEventHandler();


        if(handler.getSimpleName().equals(PlayerBackHandler.class.getSimpleName())){
            return (T) CoTeleport.instance.getPlayerBackHandler();
        }

        return null;
    }

}
