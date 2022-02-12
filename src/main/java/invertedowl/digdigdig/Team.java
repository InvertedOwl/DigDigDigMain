package invertedowl.digdigdig;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> playerList;
    private String color;
    private Entity teamEntity;
    private Entity teamTagHealth;
    private Entity teamTagColor;

    public Team(String color, ArrayList<Player> playerList) {
        this.color = color;
        this.playerList = playerList;
    }

    public Team(Player player) {
        this.color = "Default";
        ArrayList<Player> list = new ArrayList<>();
        list.add(player);
        this.playerList = list;
    }

    public Team() {
        this.color = "Default";
        ArrayList<Player> list = new ArrayList<>();
        this.playerList = list;
    }

    public void spawnEntity() {

    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Entity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(Entity teamEntity) {
        this.teamEntity = teamEntity;
    }
}
