package org.jm.spigotmc.mobwalk;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

/*************************************************************************
 *
 * J&M CONFIDENTIAL - @author Jon
 * __________________
 *
 *  [2016] J&M Plugin Development 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of J&M Plugin Development and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to J&M Plugin Development
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from J&M Plugin Development.
 */
public class MobWalk extends JavaPlugin {

    @Override
    public void onEnable() {


    }

    @Override
    public void onDisable() {


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("setmob")) {

            if (sender instanceof Player) {

                Player p = (Player) sender;
                Entity entity = p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);

                ((EntityInsentient) entity).setGoalTarget(null);
                moveTo(entity, p.getTargetBlock((Set<Material>) null, 20).getLocation());

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe console cannot execute this command."));

            }

        }

        return false;

    }

    private void moveTo(Entity entity, Location loc) {

        EntityInsentient nmsEntity = (EntityInsentient) ((CraftEntity) entity).getHandle();
        PathEntity path = nmsEntity.getNavigation().a(loc.getX(), loc.getY(), loc.getZ());
        if (path != null) nmsEntity.getNavigation().a(path, 1.0D);

    }

}
