package com.sucy.skill.facade.bukkit.entity

import com.sucy.skill.SkillAPI
import com.sucy.skill.facade.api.data.inventory.ActorInventory
import com.sucy.skill.facade.api.entity.Actor
import com.sucy.skill.facade.api.event.actor.ActorDamagedByActorEvent
import com.sucy.skill.facade.api.event.actor.DamageSource
import com.sucy.skill.facade.bukkit.data.inventory.BukkitMobInventory
import com.sucy.skill.facade.bukkit.data.inventory.BukkitPlayerInventory
import org.bukkit.Bukkit.dispatchCommand
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import java.util.*

/**
 * SkillAPIKotlin © 2018
 */
open class BukkitActor(override val entity: LivingEntity) : BukkitEntity(entity), Actor {
    override val inventory: ActorInventory
        get() = BukkitMobInventory(entity.equipment)
    override val exists: Boolean
        get() = entity.isValid
    override val dead: Boolean
        get() = entity.isDead
    override val uuid: UUID
        get() = entity.uniqueId
    override var health: Double
        get() = entity.health
        set(value) { entity.health = value }
    override var maxHealth: Double
        get() = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).value
        set(value) {
            val attr = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
            attr.baseValue = value - attr.value + attr.baseValue
        }
    override var food: Double
        get() = 0.0
        set(_) {}
    override var saturation: Double
        get() = 0.0
        set(_) {}
    override val level = 1
    
    override fun hasPermission(permission: String): Boolean {
        return entity.hasPermission(permission)
    }

    override fun executeCommand(command: String) {
        val wasOp = entity.isOp
        entity.isOp = true
        dispatchCommand(entity, command)
        entity.isOp = wasOp
    }

    override fun sendMessage(message: String) {
        entity.sendMessage(message)
    }
}