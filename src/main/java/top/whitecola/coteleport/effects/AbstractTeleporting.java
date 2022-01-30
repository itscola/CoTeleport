package top.whitecola.coteleport.effects;

import de.slikey.effectlib.effect.CircleEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import top.whitecola.coteleport.wrapper.AbstractRequest;
import top.whitecola.coteleport.wrapper.PlayerNoticer;

public abstract class AbstractTeleporting {

    public void teleportToSomeplace(Entity entity, Location to){

    }


    public Thread tpaRequestHandle(AbstractRequest request, PlayerNoticer noticer){

        return null;
    }

    public static CircleEffect playTeleportingParticle(Location location){

        return null;
    }


}
