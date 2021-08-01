package me.hazedev.ffa.stats

import com.google.gson.JsonObject
import org.bukkit.event.Listener

class FFAStats(private val jsonObject: JsonObject) : Listener {

    private companion object Properties {
        const val KILLS = "kills"
        const val DEATHS = "deaths"
        const val KILLSTREAK = "killstreak"
        const val TOP_KILLSTREAK = "top-killstreak"
    }

    constructor(): this(JsonObject())

    var kills: Int
    var deaths: Int
    var killStreak: Int
    var topKillStreak: Int

    init {
        with(jsonObject) {
            kills = get(KILLS)?.asInt ?: 0
            deaths = get(DEATHS)?.asInt ?: 0
            killStreak = get(KILLSTREAK)?.asInt ?: 0
            topKillStreak = get(TOP_KILLSTREAK)?.asInt ?: 0
        }
    }

    fun getJsonObject(): JsonObject {
        return jsonObject.apply {
            addProperty(KILLS, kills)
            addProperty(DEATHS, deaths)
            addProperty(KILLSTREAK, killStreak)
            addProperty(TOP_KILLSTREAK, topKillStreak)
        }
    }

    fun addKill() {
        kills++
        killStreak++
        if (killStreak > topKillStreak) {
            topKillStreak = killStreak
        }
    }

    fun addDeath() {
        deaths++
        killStreak = 0
    }

}