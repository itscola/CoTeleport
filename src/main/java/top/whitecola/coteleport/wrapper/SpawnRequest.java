package top.whitecola.coteleport.wrapper;

import org.bukkit.Location;
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
        super.accept();
        from.sendMessage("§a§l服务器同意了你的回城请求，即将传送。");
        thread = TeleportUtils.tpaRequestHandle(this,new PlayerNoticer(from,null));

    }

    @Override
    public void deny() {
        super.deny();
        from.sendMessage("§4§l回城请求已被服务器驳回");
    }

    @Override
    public void cancel() {
        super.cancel();
        from.sendMessage("§4回城请求已被服务器取消。");

    }


}
