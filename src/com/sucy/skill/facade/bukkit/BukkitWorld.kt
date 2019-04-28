package com.sucy.skill.facade.bukkit

import com.sucy.skill.facade.api.World
import com.sucy.skill.facade.api.data.Block
import com.sucy.skill.facade.api.entity.Actor
import com.sucy.skill.facade.bukkit.data.BukkitBlock
import com.sucy.skill.util.math.Vector3
import com.sucy.skill.util.math.toChunk
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.LivingEntity

class BukkitWorld(private val world: org.bukkit.World) : World {
    override fun isLoaded(location: com.sucy.skill.facade.api.data.Location): Boolean {
        val coords = location.coords
        return Bukkit.getWorld(location.world).isChunkLoaded(coords.x.toChunk(), coords.z.toChunk())
    }

    override fun getActorsInRadius(center: Vector3, radius: Double): List<Actor> {
        val loc = Location(world, center.x, center.y, center.z)
        val result = ArrayList<Actor>()
        forEachChunkPos(center, radius) { i, j ->
            world.getChunkAt(i, j).entities
                    .filter { it is LivingEntity && it.location.distanceSquared(loc) <= radius }
                    .forEach { result.add(it.skillAPI() as Actor) }
        }
        return result
    }

    override fun createExplosion(pos: Vector3, power: Double, fire: Boolean, damageBlocks: Boolean) {
        world.createExplosion(pos.x, pos.y, pos.z, power.toFloat(), fire, damageBlocks)
    }

    override fun getBlock(x: Int, y: Int, z: Int): Block {
        return BukkitBlock(world.getBlockAt(x, y, z))
    }
}