package invertedowl.digdigdig;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockRegen implements Listener {
    private Location blockLocation;
    private Material blockMaterial;
    private int timeInterval;
    private boolean wait = false;

    public BlockRegen(Location blockLocation, Material blockMaterial, int timeInterval) {
        this.blockLocation = blockLocation;
        this.blockMaterial = blockMaterial;
        this.timeInterval = timeInterval;
        blockLocation.getBlock().setType(blockMaterial);
        DigDigDig.instance.getServer().getPluginManager().registerEvents(this, DigDigDig.instance);
    }

    public void blockBroke() {
        new BukkitRunnable() {
            @Override
            public void run() {
                blockLocation.getBlock().setType(Material.BEDROCK);
            }
        }.runTaskLater(DigDigDig.instance, 2 /*<-- the delay */);

        if (!wait) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    blockLocation.getBlock().setType(blockMaterial);
                    wait = false;
                }
            }.runTaskLater(DigDigDig.instance, 20L * timeInterval /*<-- the delay */);
        }
    }


    // Listeners //
    @EventHandler
    public void onBlockBroken (BlockBreakEvent event) {
        if (event.getBlock().getLocation().equals(blockLocation)){
            blockBroke();
            //event.setCancelled(true);
            wait = true;
        }
    }





    // Getters and setters //
    public Location getBlockLocation() {
        return blockLocation;
    }

    public void setBlockLocation(Location blockLocation) {
        this.blockLocation = blockLocation;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public void setBlockMaterial(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }
}
