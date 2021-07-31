package me.hazedev.ffa

import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class HealthRefiller : Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val died = event.entity
        val killer = died.killer
        if (killer != null) {
            killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).value
        }
    }

}