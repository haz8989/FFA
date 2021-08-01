package me.hazedev.ffa.stats

import me.clip.placeholderapi.PlaceholderAPI
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.hazedev.ffa.FFAPlugin
import org.bukkit.entity.Player

class StatisticPlaceholders(private val plugin: FFAPlugin) : PlaceholderExpansion() {

    override fun getIdentifier(): String = "ffa"

    override fun getAuthor() = plugin.description.authors.toString()

    override fun getVersion() = plugin.description.version!!

    override fun canRegister() = !PlaceholderAPI.isRegistered(identifier)

    override fun persist() = true

    override fun onPlaceholderRequest(player: Player, params: String): String? {
        val stats = plugin.statisticManager.getStats(player)
        if (stats != null) {
            return when (params) {
                "kills" -> format(stats.kills)
                "deaths" -> format(stats.deaths)
                "kd" -> format(if (stats.deaths == 0) stats.kills.toFloat() else stats.kills.toFloat() / stats.deaths)
                "killstreak" -> format(stats.killStreak)
                "top_killstreak" -> format(stats.topKillStreak)
                else -> null
            }
        }
        return null
    }

    private fun format(amount: Float): String {
        if (amount < 0) return "-" + format(-amount)
        val amountAsInt = amount.toInt()
        return if (amount - amountAsInt == 0f) {
            String.format("%,d", amountAsInt)
        } else {
            String.format("%,.2f", amount)
        }
    }

    private fun format(amount: Int): String {
        return format(amount.toFloat())
    }

}