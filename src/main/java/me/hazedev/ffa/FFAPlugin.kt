package me.hazedev.ffa

import me.hazedev.ffa.stats.StatisticManager
import me.hazedev.ffa.stats.StatisticPlaceholders
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

class FFAPlugin : JavaPlugin() {

    val healthRefiller by lazy { HealthRefiller() }
    val statisticManager by lazy { StatisticManager(this) }
    val killFeed by lazy { KillFeed() }

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(statisticManager, this)
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            StatisticPlaceholders(this).register()
        }
        reload()
    }

    override fun onDisable() {
        save()
    }

    private fun reload() {
        saveDefaultConfig()
        reloadConfig()
        if (config.getBoolean("refill-health")) {
            Bukkit.getPluginManager().registerEvents(healthRefiller, this)
        } else {
            HandlerList.unregisterAll(healthRefiller)
        }
        if (config.isString("death-message")) {
            killFeed.deathMessage = config.getString("death-message")
            Bukkit.getPluginManager().registerEvents(killFeed, this)
        } else {
            HandlerList.unregisterAll(killFeed)
        }
    }

    private fun save() {
        statisticManager.save()
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if ("ffa" == command.name) {
            if (args.isNotEmpty()) {
                if (sender.hasPermission("ffa.reload") && "reload".equals(args[0], true)) {
                    reload()
                    sender.sendMessage("&a[FFA] Successfully reloaded".addColor())
                    return true
                }
            }
            sender.sendMessage("&cYou don't have permission to use this command".addColor())
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        val result = ArrayList<String>()
        if ("ffa" == command.name) {
            if (args.size == 1) {
                if (sender.hasPermission("ffa.reload")) {
                    result.add("reload")
                }
            }
        }
        return result
    }
}
