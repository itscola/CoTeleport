package top.whitecola.coteleport.interfaces;

import top.whitecola.coteleport.wrapper.AbstractRequest;

public interface IEventHandler {
    default void stop(){};
    default void addRequest(AbstractRequest request){};
    default void removeRequest(AbstractRequest request){};
}
