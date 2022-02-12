package invertedowl.digdigdig;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private ArrayList<Player> playerList;
    private World world;
    private ArrayList<Team> teamsList = new ArrayList<Team>();
    private ArrayList<Location> spawnLocation = new ArrayList<Location>();

    public GameManager(ArrayList<Player> playerList) {
        this.playerList = playerList;
        WorldCreator worldCreator = new WorldCreator("currentworld");
        System.out.println(worldCreator.name());
        worldCreator.generator(new ChunkGenerator() {
            @Override
            public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
                ChunkData chunk = createChunkData(world);
                if (x == 0 && z == 0) {
                    chunk.setBlock(0, 70, 0, Material.AMETHYST_BLOCK);
                }
                return chunk;
            }
        });
        System.out.println("Ready to generate");
        World world = Bukkit.getServer().createWorld(worldCreator);
        System.out.println("Generated");
        this.world = world;

        for (Player player : playerList){
            player.teleport(new Location(world, 0, 70, 0));
        }

        this.playerList = playerList;
        if (checkGame(playerList)){
            startGame(playerList);
        }


    }

    public boolean checkGame(ArrayList<Player> playerList) {
        return true;
    }

    public void startGame(ArrayList<Player> playerList) {
        world.setTime(12000);
        world.setClearWeatherDuration(1000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setDifficulty(Difficulty.EASY);
        assignTeams(playerList);
        int scale = 10;
        
        new BlockRegen(new Location(world, 30 + scale, 71, 0 - 4), Material.STONE, 1);
        new BlockRegen(new Location(world, 30 + scale, 71, 0 - 2), Material.COAL_ORE, 5);
        new BlockRegen(new Location(world, 30 + scale, 71, 0 - 0), Material.IRON_ORE, 10);
        new BlockRegen(new Location(world, 30 + scale, 71, 0 + 2), Material.DIAMOND_ORE, 60);
        new BlockRegen(new Location(world, 30 + scale, 71, 0 + 4), Material.OBSIDIAN, 120);
        new Location(world, 30 + scale - 4, 71, 0 ).getBlock().setType(Material.BEDROCK);

        new BlockRegen(new Location(world, -30 - scale, 71, 0 - 4), Material.STONE, 1);
        new BlockRegen(new Location(world, -30 - scale, 71, 0 - 2), Material.COAL_ORE, 5);
        new BlockRegen(new Location(world, -30 - scale, 71, 0 - 0), Material.IRON_ORE, 10);
        new BlockRegen(new Location(world, -30 - scale, 71, 0 + 2), Material.DIAMOND_ORE, 60);
        new BlockRegen(new Location(world, -30 - scale, 71, 0 + 4), Material.OBSIDIAN, 120);
        new Location(world, -30 - scale + 4, 71, 0 ).getBlock().setType(Material.BEDROCK);

        new BlockRegen(new Location(world, 0 - 4, 71, 30 + scale), Material.STONE, 1);
        new BlockRegen(new Location(world, 0 - 2, 71, 30 + scale), Material.COAL_ORE, 5);
        new BlockRegen(new Location(world, 0 - 0, 71, 30 + scale), Material.IRON_ORE, 10);
        new BlockRegen(new Location(world, 0 + 2, 71, 30 + scale), Material.DIAMOND_ORE, 60);
        new BlockRegen(new Location(world, 0 + 4, 71, 30 + scale), Material.OBSIDIAN, 120);
        new Location(world, 0 , 71, 30 + scale - 4).getBlock().setType(Material.BEDROCK);

        new BlockRegen(new Location(world, 0 - 4, 71, -30 - scale), Material.STONE, 1);
        new BlockRegen(new Location(world, 0 - 2, 71, -30 - scale), Material.COAL_ORE, 5);
        new BlockRegen(new Location(world, 0 - 0, 71, -30 - scale), Material.IRON_ORE, 10);
        new BlockRegen(new Location(world, 0 + 2, 71, -30 - scale), Material.DIAMOND_ORE, 60);
        new BlockRegen(new Location(world, 0 + 4, 71, -30 - scale), Material.OBSIDIAN, 120);
        new Location(world, 0 , 71, -30 - scale + 4).getBlock().setType(Material.BEDROCK);

        spawnTeamMobs();

    }

    public void spawnTeamMobs() {


        int scale = 9;
        if (teamsList.size() < 0 + 1) return;
        System.out.println("Spawning slime " + teamsList.size());
        Slime slime1 = (Slime) world.spawnEntity(new Location(world, 0.5 , 72, -30.5 - scale + 4), EntityType.SLIME);
        slime1.setSize(1);
        slime1.setAI(false);
        teamsList.get(0).setTeamEntity(slime1);

        if (teamsList.size() < 1 + 1) return;
        System.out.println("Spawning slime " + teamsList.size());
        Slime slime2 = (Slime) world.spawnEntity(new Location(world, 0.5 , 72, 30.5 + scale - 4), EntityType.SLIME);
        slime2.setSize(1);
        slime2.setAI(false);
        teamsList.get(1).setTeamEntity(slime2);

        if (teamsList.size() < 2 + 1) return;
        System.out.println("Spawning slime " + teamsList.size());

        Slime slime3 = (Slime) world.spawnEntity(new Location(world, 30.5 + scale - 4, 72, 0.5 ), EntityType.SLIME);
        slime3.setSize(1);
        slime3.setAI(false);
        teamsList.get(2).setTeamEntity(slime3);

        if (teamsList.size() < 3 + 1) return;
        System.out.println("Spawning slime " + teamsList.size());

        Slime slime4 = (Slime) world.spawnEntity(new Location(world, -30.5 - scale + 4, 72, 0.5 ), EntityType.SLIME);
        slime4.setSize(1);
        slime4.setAI(false);
        teamsList.get(3).setTeamEntity(slime4);
    }

    public void assignTeams(ArrayList<Player> playerList) {
        teamsList = new ArrayList<Team>();
        for (int i = 0; i < playerList.size(); i++) {
            if (teamsList.size() < 4){
                teamsList.add(new Team());
            }
        }
        for (int i = 0; i < playerList.size(); i++) {
            teamsList.get(i % 4).getPlayerList().add(playerList.get(i));
            playerList.get(i).sendMessage("[Debug] You will be on team " + teamsList.get(i % 4).getColor());
            playerList.get(i).sendMessage("[Debug] Total teams and their players: ");
            sendTeams(playerList.get(i));

        }
    }

    public void addPlayer(Player player) {}

    public void checkWin() {

    }

    public void sendTeams(Player player) {
        for (Team team : teamsList){
            player.sendMessage(ChatColor.GRAY + "- " + team.getColor());
            for (Player tplayer : team.getPlayerList()) {
                player.sendMessage(ChatColor.BLUE +""+ ChatColor.ITALIC + "     - " + tplayer.getDisplayName());
            }
        }
    }

    public void endGame() {

    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }

    public ArrayList<Location> getSpawnLocation() {
        return spawnLocation;
    }
}
