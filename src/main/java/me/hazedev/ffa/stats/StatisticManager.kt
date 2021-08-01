package me.hazedev.ffa.stats

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonStreamParser
import me.hazedev.ffa.FFAPlugin
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.UUID

class StatisticManager(plugin: FFAPlugin) : Listener {

    private val gson = Gson()

    private val statsMap = HashMap<UUID, FFAStats>()
    private val statsDir = File(plugin.dataFolder, "stats")

    private fun getStatsFile(uniqueId: UUID): File {
        return File(statsDir, "$uniqueId.json")
    }

    fun save() {
        for (entry in statsMap) {
            val file = getStatsFile(entry.key)
            if (!file.exists()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            FileWriter(file).use { writer ->
                gson.toJson(entry.value.getJsonObject(), writer)
            }
        }
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val uniqueId = event.player.uniqueId
        if (!statsMap.containsKey(uniqueId)) {
            val file = getStatsFile(uniqueId)
            val stats = if (file.exists()) {
                val jsonObject: JsonObject
                BufferedReader(FileReader(file)).use { reader ->
                    jsonObject = JsonStreamParser(reader).next().asJsonObject
                }
                FFAStats(jsonObject)
            } else {
                FFAStats()
            }
            statsMap[uniqueId] = stats
        }
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val died = event.entity
        val killer = died.killer
        if (killer != null) {
            getStats(died)?.addDeath()
            getStats(killer)?.addKill()
        }
    }

    fun getStats(player: Player): FFAStats? {
        return statsMap[player.uniqueId]
    }

}
