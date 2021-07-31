package me.hazedev.ffa

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

class FFAPlugin : JavaPlugin() {

    private lateinit var healthRefiller: HealthRefiller

    override fun onLoad() {
        healthRefiller = HealthRefiller()
    }

    override fun onEnable() {
        reload()
    }

    override fun onDisable() {

    }

    private fun reload() {
        saveDefaultConfig()
        reloadConfig()
        if (config.getBoolean("refill-health")) {
            Bukkit.getPluginManager().registerEvents(healthRefiller, this)
        } else {
            HandlerList.unregisterAll(healthRefiller)
        }

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
                    return true;
                }
            }
            sender.sendMessage("&cYou don't have permission to use this command".addColor())
        }
        return true;
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
