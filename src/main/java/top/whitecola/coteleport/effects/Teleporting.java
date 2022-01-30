package top.whitecola.coteleport.effects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.PlayerNoticer;

public class Teleporting extends AbstractTeleporting{
    @Override
    public void teleportToSomeplace(Entity entity, Location to) {
        super.teleportToSomeplace(entity, to);
    }


    @Override
    public Thread tpaRequestHandle(AbstractRequest request, PlayerNoticer noticer) {
        return super.tpaRequestHandle(request, noticer);
    }
}
