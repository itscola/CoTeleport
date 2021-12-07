package top.whitecola.coteleport;

import de.slikey.effectlib.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.whitecola.commandhandler.HiCommand;
import top.whitecola.coteleport.commands.Spawn;
import top.whitecola.coteleport.commands.Tpa;
import top.whitecola.coteleport.commands.Tpaccept;
import top.whitecola.coteleport.commands.Tpadeny;
import top.whitecola.coteleport.handler.PlayerTeleportEventHandler;
import top.whitecola.coteleport.listeners.PlayerListener;
import top.whitecola.threader.HiThread;

import java.util.List;

public class CoTeleport extends JavaPlugin {
    public static CoTeleport instance = null;
    {
        instance = this;
    }

    public static HiThread thread = new HiThread("tpt",100);
    {
        thread.start();
    }
    public PlayerTeleportEventHandler teleportEventHandler = new PlayerTeleportEventHandler();
    public HiCommand commands = new HiCommand(instance,"ct");
    public EffectManager effectManager = new EffectManager(this);

    @Override
    public void onLoad() {
        this.getLogger().info("CoTeleport is enabled!");
        this.getLogger().info("The thread of coteleport started!");

    }

    @Override
    public void onEnable() {
        init();
    }

    private void init() {
        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
    }

    private void registerCommands(){
        commands.addCommand(new Tpa());
        commands.addCommand(new Tpaccept());
        commands.addCommand(new Tpadeny());
        commands.addCommand(new Spawn());
    }


    @Override
    public void onDisable() {
        this.teleportEventHandler.stop();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commands.onCommand(sender,command,label,args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return commands.onTabComplete(sender,command,alias,args);
    }
}
