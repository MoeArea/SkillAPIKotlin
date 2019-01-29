package com.sucy.skill.api.profession

import com.sucy.skill.SkillAPI
import com.sucy.skill.api.Levelable
import com.sucy.skill.facade.api.entity.Actor
import kotlin.test.assertEquals

class ProfessionSet {
    private val professions = HashMap<String, ProfessionProgress>()

    val all: Collection<ProfessionProgress>
        get() { return professions.values }

    val main: ProfessionProgress?
        get() { return this[SkillAPI.settings.account.mainGroup]; }

    operator fun ProfessionSet.get(group: String): ProfessionProgress? {
        return professions[group]
    }

    operator fun ProfessionSet.set(group: String, profession: ProfessionProgress) {
        professions.put(group, profession)
    }

    fun isProfessed(): Boolean {
        return !professions.isEmpty()
    }

    fun isProfessed(group: String): Boolean {
        return professions.containsKey(group)
    }
}