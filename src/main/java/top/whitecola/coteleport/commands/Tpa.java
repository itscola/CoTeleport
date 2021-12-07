package top.whitecola.coteleport.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.coteleport.struct.PlayerRequest;
import top.whitecola.coteleport.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ItsACommand(CommandNmae = "tpa",premission = "ct.tpa")
public class Tpa implements ICommand {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player))
            return false;

        Player fromP = (Player) commandSender;

        if(args.length<2)
            return false;

        if(fromP.getName().equalsIgnoreCase(args[1])){
            commandSender.sendMessage("§e无法向自己发送传送请求");
            return true;
        }

        Player toPlayer = Bukkit.getPlayer(args[1]);

        if(toPlayer==null || !toPlayer.isOnline()){
            commandSender.sendMessage("§e无法找到玩家 "+args[1]+" ，该玩家是否在线?");
            return true;
        }

        PlayerRequest playerRequest = new PlayerRequest(fromP,toPlayer);
        playerRequest.submitRequest();


        return true;
    }

    public List<String> getArgs() {
        return Arrays.asList("[Player]");
    }

    public List<String> handleArg(CommandSender sender, String handleArg) {
        if (!handleArg.equalsIgnoreCase("[Player]")) {
            return Arrays.asList("");
        }
        return Bukkit.getOnlinePlayers().stream().map(i->i.getName()).collect(Collectors.toList());

    }

    public String getUsage() {
        return "/ct tpa <Player>";
    }

    public String getUsageDescripition() {
        return "向指定玩家发送传送请求";
    }
}
