# FFA Plugin
This is an FFA plugin for Spigot 1.12+ created upon request

## Features
- Refill health after a kill (toggleable)
- Stats (See [Placeholders](#placeholders))

## Commands
*Command | Permission - Description*
- `/ffa reload` | `ffa.reload` - Reloads the plugin

## Config
[Default config.yml](src/main/resources/config.yml)
- `refill-health: true` - Whether health should be refilled after a kill

## Placeholders
- `%ffa_kills%` - Kills
- `%ffa_deaths%` - Deaths
- `%ffa_kd%` - Kill/Death Ratio
- `%ffa_killstreak%` - Current Killstreak
- `%ffa_top_killstreak%` - Best Killstreak