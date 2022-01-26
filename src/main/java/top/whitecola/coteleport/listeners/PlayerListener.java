package top.whitecola.coteleport.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.wrapper.BackRequest;
import top.whitecola.coteleport.wrapper.PlayerRequest;

public class PlayerListener implements Listener {
    @EventHandler
    public void playerMoveListener(PlayerMoveEvent e){

        if(e.getFrom().getBlockX()==e.getTo().getBlockX() && e.getFrom().getBlockY()==e.getTo().getBlockY()&& e.getFrom().getBlockZ()==e.getTo().getBlockZ()){
            return;
        }
        PlayerRequest playerRequest = CoTeleport.instance.teleportEventHandler.getPlayerRequestByFrom(e.getPlayer());
        if(playerRequest==null)
            return;

        if(playerRequest.getThread()==null)
            return;

        e.setCancelled(true);


    }

//    @EventHandler
//    public void PlayerShiftEvent(PlayerToggleSneakEvent e){
//        PlayerRequest playerRequest = CoTeleport.instance.teleportEventHandler.getPlayerRequestByFrom(e.getPlayer());
//        if(playerRequest==null)
//            return;
//
//        if(playerRequest.thread==null)
//            return;
//
//        playerRequest.cancel();
//
//    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){
        PlayerRequest playerRequest = CoTeleport.instance.teleportEventHandler.getPlayerRequestByFrom(e.getPlayer());
        if(playerRequest!=null){
            CoTeleport.instance.teleportEventHandler.getRequests().remove(playerRequest);
        }else if((playerRequest = CoTeleport.instance.teleportEventHandler.getPlayerRequestByTo(e.getPlayer()))!=null){
            CoTeleport.instance.teleportEventHandler.getRequests().remove(playerRequest);
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
