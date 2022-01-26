package top.whitecola.coteleport.wrapper;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.utils.TeleportUtils;

public class SpawnRequest extends AbstractRequest{

    public SpawnRequest(Player from, Location to){
        this.from = from;
        this.tolcation = to;
    }

    @Override
    public void submitRequest() {
        from.sendMessage("§e已经向服务器发起回城请求。");
    }

    @Override
    public void accept() {
        playsound();
        from.sendMessage("§a§l服务器同意了你的回城请求，即将传送。");
        time = System.currentTimeMillis();
        thread = TeleportUtils.tpaRequestHandle(this,new PlayerNoticer(from,null));

    }

    @Override
    public void deny() {
        from.sendMessage("§4§l回城请求已被服务器驳回，原因："+"爷还没把功能写好。");
    }

    @Override
    public void cancel() {
        from.sendMessage("§4回城请求已被服务器取消。");

    }

    @Override
    public void playsound() {
        from.getPlayer().playSound(from.getLocation(), Sound.ENTITY_ITEM_PICKUP,2,2);


    }

}
