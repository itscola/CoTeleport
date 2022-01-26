package top.whitecola.coteleport.wrapper;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.interfaces.IRquest;

import java.util.Optional;

public abstract class AbstractRequest implements IRquest {
    protected Player from;
    protected Player to;
    protected Location tolcation;
    protected long time;
    protected Thread thread;

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
}
