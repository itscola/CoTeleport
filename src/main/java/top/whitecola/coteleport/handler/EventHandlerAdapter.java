package top.whitecola.coteleport.handler;

import top.whitecola.coteleport.interfaces.IEventHandler;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.BackRequest;
import top.whitecola.coteleport.wrapper.PlayerRequest;

public class EventHandlerAdapter implements IEventHandler {

    @Override
    public void addRequest(AbstractRequest request) {
        requests.add(request);
    }

    @Override
    public void removeRequest(AbstractRequest request) {
        requests.remove(request);
    }


    public void addRequest(PlayerRequest playerRequest) {
        addRequest((AbstractRequest) playerRequest);
    }

    public void addRequest(BackRequest backRequest){
        addRequest((AbstractRequest) backRequest);
    }

    public void removeRequest(PlayerRequest playerRequest) {
        removeRequest((AbstractRequest) playerRequest);
    }

//    public void removeRequest(BackRequest backRequest){
//        removeRequest((AbstractRequest) backRequest);
//
//    }



}
