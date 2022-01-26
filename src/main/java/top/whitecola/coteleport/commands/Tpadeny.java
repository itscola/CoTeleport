package top.whitecola.coteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.wrapper.PlayerRequest;

import java.util.Arrays;
import java.util.List;

@ItsACommand(CommandNmae = "tpadeny",premission = "ct.tpadeny")
public class Tpadeny implements ICommand {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))
            return false;

        Player toPlayer = (Player) commandSender;

        if (args.length != 1)
            return false;

        PlayerRequest toPlayerRequest = CoTeleport.instance.teleportEventHandler.getPlayerRequestByTo(toPlayer);

        if (toPlayerRequest == null) {
            toPlayer.sendMessage("§4你没有可以拒绝的请求，或请求已过期。");
            return true;
        }

        if(toPlayerRequest.getThread()!=null){
            toPlayer.sendMessage("§4拒绝请求操作已被驳回，对方正在传送过程中。");
            return true;
        }
        toPlayerRequest.deny();



        return true;
    }


    public List<String> getArgs() {
        return Arrays.asList("");
    }

    public List<String> handleArg(CommandSender sender, String handleArg) {
        return Arrays.asList("");
    }

    public String getUsage() {
        return "/ct tpadeny";
    }

    public String getUsageDescripition() {
        return "拒绝玩家的传送请求。";
    }
}
