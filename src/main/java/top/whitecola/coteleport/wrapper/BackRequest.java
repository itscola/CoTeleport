package top.whitecola.coteleport.wrapper;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.utils.TeleportUtils;

public class BackRequest extends AbstractRequest{

    public BackRequest(Player from, Location location){
        this.from = from;
        this.tolcation = location;
    }

    @Override
    public void submitRequest() {
        super.submitRequest();
        from.sendMessage("§e你上次死在了 "+from.getWorld().getName()+" 世界 "+from.getLocation().getBlockX()+" "+from.getLocation().getBlockY()+" "+from.getLocation().getBlockZ()+" 输入 /ct back 返回上一个地点。");
        from.getPlayer().getPlayer().spigot().sendMessage
                (new ComponentBuilder("§2§l或点击此处回到上个地点").event
                        (new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/ct back").create()))
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ct back")).create());


    }

    @Override
    public void accept() {
        time = System.currentTimeMillis();
        thread = TeleportUtils.tpaRequestHandle(this,new PlayerNoticer(from,null));
    }

    @Override
    public void deny() {
        super.deny();
        from.sendMessage("§4回到上一个地点的请求已被服务器拒绝。");
    }


    @Override
    public void cancel() {
        from.sendMessage("传送取消。");
        super.cancel();
    }

}
