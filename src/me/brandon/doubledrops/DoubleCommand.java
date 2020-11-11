package me.brandon.doubledrops;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Objects;

public class DoubleCommand implements CommandExecutor, Listener {

    boolean drops_enabled = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        drops_enabled = !drops_enabled;
        return true;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (drops_enabled) { // check if drops are enabled
            e.isCancelled();
            Block block = e.getBlock();
            ItemStack playerTool = e.getPlayer().getInventory().getItemInMainHand();

            Collection<ItemStack> drops = block.getDrops(playerTool);
            block.setType(Material.AIR);
            drops.forEach(itemStack -> { // double the item drops
                int dropAmount = (itemStack.getAmount() * 2);
                itemStack.setAmount(dropAmount);
                Objects.requireNonNull(block.getLocation().getWorld().dropItemNaturally(block.getLocation(), itemStack));

            });
        }
    }
}
