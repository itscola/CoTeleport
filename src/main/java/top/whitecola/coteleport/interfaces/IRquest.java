package top.whitecola.coteleport.interfaces;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IRquest {

    void submitRequest();
    void accept();
    void deny();
    void cancel();
    void playsound();

    void setTime(long time);
}
