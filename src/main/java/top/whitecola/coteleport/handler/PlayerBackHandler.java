package top.whitecola.coteleport.handler;

import org.bukkit.entity.Player;
import top.whitecola.coteleport.interfaces.IEventHandler;
import top.whitecola.coteleport.struct.AbstractRequest;
import top.whitecola.coteleport.struct.BackRequest;
import top.whitecola.coteleport.utils.PlayerUtils;

import java.util.Vector;

public class PlayerBackHandler implements IEventHandler {
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
        if(request instanceof AbstractRequest){
            backRequests.remove((BackRequest) request);
        }
    }

    public BackRequest getBackRequestByPlayer(Player player){
        for(int i=0;i<backRequests.size();i++){
            if(PlayerUtils.isSamePlayer(backRequests.get(i).getFrom(),player)){
                return backRequests.get(i);
            }

        }
        return null;
    }
}
