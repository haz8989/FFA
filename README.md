# FFA Plugin 
[<img src="https://img.shields.io/github/v/release/haz8989/FFA">](https://github.com/haz8989/FFA/releases)<br>
This is an FFA plugin for Spigot 1.12+ created upon request<br>

## Features
- Refill health after a kill (toggleable)
- Stats (See [Placeholders](#placeholders))

## Commands
*Command | Permission - Description*
- `/ffa reload` | `ffa.reload` - Reloads the plugin

## Config
[Default config.yml](src/main/resources/config.yml)
- `refill-health` - Whether health should be refilled after a kill

## Placeholders
- `%ffa_kills%` - Kills
- `%ffa_deaths%` - Deaths
- `%ffa_kd%` - Kill/Death Ratio
- `%ffa_killstreak%` - Current Killstreak
- `%ffa_top_killstreak%` - Best Killstreak
