package invertedowl.digdigdig;

import invertedowl.digdigdig.commands.StartGameCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class DigDigDig extends JavaPlugin {

    public static DigDigDig instance;
    public static GameManager gameInstance;
    public final File templateWorlds = new File(getDataFolder().getPath().toLowerCase() + "/templateworlds");
    public final File currentWorld = new File(getDataFolder().getPath().toLowerCase() + "/currentworld");


    @Override
    public void onEnable() {
        currentWorld.delete();
        System.out.println(templateWorlds.getPath() + " is being created");
        System.out.println(templateWorlds.mkdir());
        currentWorld.mkdir();
        instance = this;
        getCommand("startGame").setExecutor(new StartGameCommand());
    }

    @Override
    public void onDisable() {
        for (Player player : gameInstance.getPlayerList()) {
            player.teleport(getServer().getWorlds().get(0).getSpawnLocation());
        }
        Bukkit.unloadWorld(gameInstance.getWorld(), false);

    }

    public static void deleteDirectoryLegacyIO(File file) {

        File[] list = file.listFiles();
        if (list != null) {
            for (File temp : list) {
                //recursive delete
                System.out.println("Visit " + temp);
                deleteDirectoryLegacyIO(temp);
            }
        }

        if (file.delete()) {
            System.out.printf("Delete : %s%n", file);
        } else {
            System.err.printf("Unable to delete file or directory : %s%n", file);
        }

    }
}
