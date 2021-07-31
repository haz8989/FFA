package me.hazedev.ffa

import org.bukkit.ChatColor

fun String.addColor(): String {
    return ChatColor.translateAlternateColorCodes('&', this)
}