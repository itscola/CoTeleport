package top.whitecola.coteleport.utils;

import de.slikey.effectlib.effect.CircleEffect;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.struct.AbstractRequest;
import top.whitecola.coteleport.struct.PlayerNoticer;

public class TeleportUtils {

    public static void teleportPlace(Entity entity, Location to){
        Bukkit.getScheduler().runTask(CoTeleport.instance,()->{
            if(entity instanceof Player){
                Player p = (Player) entity;
                p.teleport(to);
                p.playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT,2,2);
                playTeleportingParticle(new Location(p.getLocation().getWorld(),p.getLocation().getX(),p.getLocation().getY()+1,p.getLocation().getZ()));
                return;
            }
            entity.teleport(to);
            return;
        });
    }

    public static void teleport(Player from,Player to){
        teleportPlace(from,to.getLocation());
    }

    public static Thread tpaRequestHandle(AbstractRequest request, PlayerNoticer noticer){
        Thread th = new Thread(
            ()->{
                Location fromLocation = request.getFrom().getLocation();
                Location loc = new Location(fromLocation.getWorld(),fromLocation.getX(),fromLocation.getY()+1,fromLocation.getZ());
                playTeleportingParticle(loc);
                while(request.getTime()!=-1){

                    if((System.currentTimeMillis()-request.getTime())>=3000){
                        request.setTime(-1);
                        break;
                    }

                    if(request.getTime()==-2){
                        return;
                    }

                    noticer.getP1().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§l即将传送,期间无法移动。"));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                teleportPlace(request.getFrom(), request.getTolcation());

                noticer.getP1().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§l传送完成！"));

                if(noticer.getP2()!=null){
                    noticer.getP2().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§l传送完成！"));
                }


                request.setTime(-1);
                CoTeleport.instance.teleportEventHandler.getRequests().remove(request);
                return;

            }
        );
        CoTeleport.thread.addTask(th);
        return th;
    }

    public static CircleEffect playTeleportingParticle(Location location){
        CircleEffect circleEffect = new CircleEffect(CoTeleport.instance.effectManager);
        circleEffect.radius = 1.2f;
        circleEffect.particle = Particle.SPELL_WITCH;
        circleEffect.iterations = 35;
        circleEffect.particleCount = 10;
        circleEffect.setLocation(location);
        circleEffect.start();
        return circleEffect;
    }
}
