package top.whitecola.coteleport.wrapper;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.handler.PlayerBackHandler;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;
import top.whitecola.coteleport.interfaces.IRquest;
import top.whitecola.coteleport.utils.HandlerUtils;
import java.util.Optional;

public abstract class AbstractRequest implements IRquest {
    protected Player from;
    protected Player to;
    protected Location tolcation;
    protected long time;
    protected Thread thread;

    @Override
    public void submitRequest() {
        playsound();
        addRequestOrRemoving();
    }

    @Override
    public void accept() {
        playsound();
        time = System.currentTimeMillis();
    }

    @Override
    public void deny() {
        playsound();
        setTime(-2);
        addRequestOrRemoving();
    }

    @Override
    public void cancel() {
        setTime(-2);
        addRequestOrRemoving();
        thread.interrupt();
    }

    @Override
    public void playsound() {
        from.getPlayer().playSound(from.getLocation(), Sound.ENTITY_ITEM_PICKUP, 2, 2);
        if (to != null)
            to.getPlayer().playSound(from.getLocation(), Sound.ENTITY_ITEM_PICKUP, 2, 2);
    }

    public Player getFrom() {
        return from;
    }

    public void setFrom(Player from) {
        this.from = from;
    }

    public Location getTolcation() {
        return tolcation;
    }

    public void setTolcation(Location tolcation) {
        this.tolcation = tolcation;
    }

    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    public Thread getThread() {
        return thread;
    }

    public Optional<Player> getTo() {
        return Optional.ofNullable(to);
    }

    public void setTo(Player to) {
        this.to = to;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    private void addRequestOrRemoving(){
        if(this instanceof PlayerRequest) {
            if(getThread()==null && time!=-2){
                HandlerUtils.getHandler(PlayerTeleportEventHandler.class).addRequest(this);
            }else {
                HandlerUtils.getHandler(PlayerTeleportEventHandler.class).removeRequest(this);
            }
        }


        if(this instanceof BackRequest) {
            if (getThread() == null && time!=-2) {
                HandlerUtils.getHandler(PlayerBackHandler.class).addRequest(this);
            } else {
                HandlerUtils.getHandler(PlayerBackHandler.class).removeRequest(this);
            }

        }


        if(this instanceof SpawnRequest) //TODO SpawnHandler
            return;
    }



}