package top.whitecola.coteleport.struct;

import org.bukkit.entity.Player;

public class PlayerNoticer {
    private Player p1;
    private Player p2;

    public PlayerNoticer(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }


}
