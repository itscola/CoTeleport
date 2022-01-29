package top.whitecola.coteleport.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.handler.PlayerBackHandler;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;
import top.whitecola.coteleport.utils.HandlerFactory;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.BackRequest;
import top.whitecola.coteleport.wrapper.PlayerRequest;

public class PlayerListener implements Listener {
    @EventHandler
    public void playerMoveListener(PlayerMoveEvent e){

        if(e.getFrom().getBlockX()==e.getTo().getBlockX() && e.getFrom().getBlockY()==e.getTo().getBlockY()&& e.getFrom().getBlockZ()==e.getTo().getBlockZ()){
            return;
        }
        PlayerRequest playerRequest = HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getPlayerRequestByFrom(e.getPlayer());
        if(playerRequest==null)
            return;

        if(playerRequest.getThread()==null)
            return;

        e.setCancelled(true);


    }

    @EventHandler
    public void PlayerShiftEvent(PlayerToggleSneakEvent e){
        AbstractRequest request = HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getPlayerRequestByFrom(e.getPlayer());
        if(request==null && (request = HandlerFactory.getHandler(PlayerBackHandler.class).getBackRequestByPlayer(e.getPlayer()))==null)
            return;


        if(request.getThread()==null)
            return;

        request.cancel();

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){
        PlayerRequest playerRequest = HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getPlayerRequestByFrom(e.getPlayer());
        if(playerRequest!=null){
            HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getRequests().remove(playerRequest);
        }else if((playerRequest = HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getPlayerRequestByTo(e.getPlayer()))!=null){
            HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getRequests().remove(playerRequest);
        }


        BackRequest backRequest = CoTeleport.instance.getPlayerBackHandler().getBackRequestByPlayer(e.getPlayer());
        if(backRequest!=null){
            backRequest.cancel();
        }
        return;
    }

    @EventHandler
    public void onPlayerDead(PlayerDeathEvent e){
        new BackRequest(e.getEntity().getPlayer(),e.getEntity().getLocation()).submitRequest();
    }
}
