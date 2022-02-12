package invertedowl.digdigdig.commands;

import invertedowl.digdigdig.DigDigDig;
import invertedowl.digdigdig.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StartGameCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = ((Player) sender);
            if (player.hasPermission("ddd.startgame")){
                player.sendMessage("Warning expect lag!");
                ArrayList<Player> playerList = new ArrayList<>();
                playerList.add(player);
                DigDigDig.gameInstance = new GameManager(playerList);
            }
        } else {
            sender.sendMessage("Console cannot run this command.");
        }
        return false;
    }
}
