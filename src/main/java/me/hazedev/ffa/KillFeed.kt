package me.hazedev.ffa

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class KillFeed : Listener {

    lateinit var deathMessage: String

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val died = event.entity
        died.killer?.let { killer ->
            var deathMessage = deathMessage
                .replace("{world}", died.location.world.name)
                .replace("{killer}", killer.displayName)
                .replace("{died}", died.displayName)
                .replace("{killer_health}", "%.2f".format(killer.health))
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                deathMessage = PlaceholderAPI.setPlaceholders(died, event.deathMessage)
            }
            event.deathMessage = deathMessage
        }
    }

}