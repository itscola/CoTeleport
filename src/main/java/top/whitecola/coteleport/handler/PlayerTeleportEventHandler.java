package top.whitecola.coteleport.handler;

import org.bukkit.entity.Player;
import top.whitecola.coteleport.interfaces.IEventHandler;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.PlayerRequest;
import top.whitecola.coteleport.utils.PlayerUtils;
import top.whitecola.threader.HiThread;

import java.util.Vector;

public class PlayerTeleportEventHandler implements IEventHandler {
    private Vector<PlayerRequest> requests = new Vector<>();
    private HiThread thread = new HiThread("PlayerRequestRemover",1000);

    public PlayerTeleportEventHandler(){
        thread.start();
        thread.addTask(()->{
            for(int i=0;i<requests.size();i++){
                AbstractRequest pr = requests.get(i);
                if((System.currentTimeMillis()-pr.getTime())>=60000){
                    pr.getFrom().sendMessage("传送请求已超时: "+pr.getFrom().getName()+" -> "+(pr.getTo().isPresent()?pr.getTo().get().getName():pr.getTolcation()));

                    pr.getTo().ifPresent(player->{
                        player.sendMessage("传送请求已超时: "+pr.getFrom().getName()+" -> "+player.getName());
                    });
                    pr.setTime(-1);
                    requests.remove(pr);
                    i = i-1;
                }
            }
        });
    }

    public void addRequest(PlayerRequest request){

        for(int i=0;i<requests.size();i++) {


            PlayerRequest playerRequest = requests.get(i);
            if (PlayerUtils.isSamePlayer(playerRequest.getFrom(), request.getTo().get()) || PlayerUtils.isSamePlayer(playerRequest.to, request.getTo().get())) {
                requests.remove(playerRequest);
                i = i - 1;
            }

        }




        requests.add(request);
        return;
    }

    public Vector<PlayerRequest> getRequests(){
        return this.requests;
    }

    public PlayerRequest getPlayerRequest(Player from,Player to){
        for(PlayerRequest pr : requests){
            if(PlayerUtils.isSamePlayer(pr.getFrom(),from) && PlayerUtils.isSamePlayer(pr.to,to)){
                return pr;
            }
        }
        return null;
    }

    public PlayerRequest getPlayerRequestByTo(Player p) {
        for (PlayerRequest pr : requests) {
            if(PlayerUtils.isSamePlayer(pr.to,p)){
                return pr;
            }
        }
        return null;
    }

    public PlayerRequest getPlayerRequestByFrom(Player p) {
        for (PlayerRequest pr : requests) {
            if(PlayerUtils.isSamePlayer(pr.getFrom(),p)){
                return pr;
            }
        }
        return null;
    }

    @Override
    public void removeRequest(AbstractRequest request) {
        requests.remove(request);
    }
}
