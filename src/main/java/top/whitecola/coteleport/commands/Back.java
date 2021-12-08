package top.whitecola.coteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.struct.BackRequest;
import top.whitecola.coteleport.utils.PlayerUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

@ItsACommand(CommandNmae = "back",premission = "ct.back")
public class Back implements ICommand {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return false;

        Player fromP = (Player) commandSender;

        Vector<BackRequest> backRequests = CoTeleport.instance.getPlayerBackHandler().getBackRequests();
        if(backRequests==null)
            return false;

        for(BackRequest backRequest : backRequests){
            if(PlayerUtils.isSamePlayer(backRequest.getFrom(),fromP)){
                backRequest.accept();
                return true;
            }
        }

        fromP.sendMessage("§4上一个地点已过期或不存在，无法传送。");
        return true;
    }

    public List<String> getArgs() {
        return Arrays.asList("");
    }

    public List<String> handleArg(CommandSender sender, String handleArg) {

        return Arrays.asList("");

    }

    public String getUsage() {
        return "/ct back";
    }

    public String getUsageDescripition() {
        return "回到上一个死亡地点。";
    }
}
