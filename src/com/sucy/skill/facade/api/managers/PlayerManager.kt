package com.sucy.skill.facade.api.managers

import com.sucy.skill.SkillAPI
import com.sucy.skill.facade.api.entity.Player
import java.util.*

/**
 * SkillAPIKotlin © 2018
 */
interface PlayerManager {
    fun getPlayer(uuid: UUID): Player?

    fun getPlayer(name: String): Player? {
        val uuid = SkillAPI.entityData.playerIds.inverse()[name]
        return if (uuid == null) null else getPlayer(uuid)
    }

    fun isPlayerOnline(uuid: UUID): Boolean {
        return getPlayer(uuid) != null
    }

    fun isPlayerOnline(name: String): Boolean {
        return getPlayer(name) != null
    }
}