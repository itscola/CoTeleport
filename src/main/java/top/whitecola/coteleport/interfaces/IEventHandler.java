package top.whitecola.coteleport.interfaces;

import top.whitecola.coteleport.wrapper.AbstractRequest;

import java.util.Vector;

public interface IEventHandler {
    Vector<AbstractRequest> requests = new Vector<>();
    default void addRequest(AbstractRequest request){};
    default void removeRequest(AbstractRequest request){};
    default void stop(){};
}
