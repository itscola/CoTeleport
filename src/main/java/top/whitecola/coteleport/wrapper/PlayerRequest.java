package top.whitecola.coteleport.wrapper;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.utils.TeleportUtils;


public class PlayerRequest extends AbstractRequest{
   public Player to;

    public PlayerRequest(Player from,Player to){
        this.from = from;
        this.to = to;
        this.tolcation = to.getLocation();
    }
    @Override
    public void submitRequest(){
        super.submitRequest();

        from.getPlayer().sendMessage("§e已向玩家 "+to.getName()+" 发送传送请求。");
        to.getPlayer().sendMessage("-------------------------------------");
        to.getPlayer().sendMessage("§e§l收到来自玩家"+from.getName()+" 的传送到你这里的请求，输入 /ct tpaccept 同意请求，或点击下方。");
        to.getPlayer().getPlayer().spigot().sendMessage
                (new ComponentBuilder("§2§l■■■■■■■■■■■[ 同意 ]■■■■■■■■■■■").event
                        (new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/ct tpaccept").create()))
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ct tpaccept")).create());
        to.getPlayer().spigot().sendMessage

                (new ComponentBuilder("§4§l■■■■■■■■■■■[ 拒绝 ]■■■■■■■■■■■").event
                        (new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("/ct tpadeny").create()))
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ct tpadeny")).create());
        to.getPlayer().sendMessage("-------------------------------------");

    }
    @Override
    public void accept(){
        super.accept();
        to.getPlayer().sendMessage("§e等待 "+from.getName()+" 传送。");
        thread = TeleportUtils.tpaRequestHandle(this,new PlayerNoticer(from,to));
    }

    @Override
    public void deny(){
        super.deny();
        to.getPlayer().sendMessage("§4已拒绝来自 "+ from.getPlayer().getName()+" 的传送请求。");
        from.sendMessage("§4"+to.getPlayer().getName()+" 拒绝了你的传送请求。");
    }

    @Override
    public void cancel(){
        super.cancel();
        to.getPlayer().sendMessage("§e对方已取消传送。");
        from.getPlayer().sendMessage("§e传送取消。-> [SHIFT]");

    }




}
