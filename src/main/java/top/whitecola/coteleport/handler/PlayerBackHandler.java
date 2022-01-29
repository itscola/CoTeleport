package top.whitecola.coteleport.handler;

import org.bukkit.entity.Player;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.BackRequest;
import top.whitecola.coteleport.utils.PlayerUtils;

import java.util.Vector;

public class PlayerBackHandler extends EventHandlerAdapter {
    Vector<BackRequest> backRequests = new Vector<>();

    @Override
    public void addRequest(AbstractRequest request) {
        this.removeRequest(this.getBackRequestByPlayer(request.getFrom()));
        backRequests.add((BackRequest) request);
        return;
    }

    public Vector<BackRequest> getBackRequests() {
        return backRequests;
    }

    @Override
    public void removeRequest(AbstractRequest request) {
        backRequests.remove((BackRequest) request);
    }

    public BackRequest getBackRequestByPlayer(Player player){
        for(BackRequest backRequest : backRequests){

            if(PlayerUtils.isSamePlayer(backRequest.getFrom(),player)){
                return backRequest;
            }
        }
        return null;
    }
}
