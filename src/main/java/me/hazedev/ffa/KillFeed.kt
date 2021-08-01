package me.hazedev.ffa

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class KillFeed : Listener {

    var deathMessage: String? = null

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        deathMessage?.let { deathMessage ->
            val died = event.entity
            died.killer?.let { killer ->
                event.deathMessage = deathMessage.replace("{world}", died.location.world.name)
                    .replace("{killer}", killer.displayName)
                    .replace("{died}", died.displayName)
                    .replace("{killer_health}", "%.2f".format(killer.health))
            }
        }
    }

}