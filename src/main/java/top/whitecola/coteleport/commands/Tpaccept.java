package top.whitecola.coteleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.coteleport.CoTeleport;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;
import top.whitecola.coteleport.utils.HandlerFactory;
import top.whitecola.coteleport.wrapper.PlayerRequest;

import java.util.Arrays;
import java.util.List;

@ItsACommand(CommandNmae = "tpaccept",premission = "ct.tpaccept")
public class Tpaccept  implements ICommand {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player))
            return false;

        Player toPlayer = (Player) commandSender;

        if(args.length!=1)
            return false;

        PlayerRequest toPlayerRequest = HandlerFactory.getHandler(PlayerTeleportEventHandler.class).getPlayerRequestByTo(toPlayer);
        if(toPlayerRequest==null){
            toPlayer.sendMessage("§4你没有被传送请求，或该请求已过期。");
            return true;
        }

        toPlayerRequest.accept();
        return true;
    }

    public List<String> getArgs() {
        return Arrays.asList("");
    }

    public List<String> handleArg(CommandSender sender, String handleArg) {
        return Arrays.asList("");
    }

    public String getUsage() {
        return "/ct tpaccept";
    }

    public String getUsageDescripition() {
        return "接受玩家的传送请求。";
    }
}
