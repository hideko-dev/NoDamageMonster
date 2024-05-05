package dev.hideko.nodamagemonster

import dev.hideko.nodamagemonster.listener.EntityDamage
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class NoDamageMonster : JavaPlugin() {

    companion object {
        lateinit var instance: NoDamageMonster
        lateinit var configure: FileConfiguration
    }

    override fun onEnable() {
        instance = this
        val configFile = File(dataFolder, "config.yml")
        if(!configFile.exists()) {
            configFile.parentFile.mkdirs()
            saveResource("config.yml", false)
        }
        configure = YamlConfiguration.loadConfiguration(configFile)
        server.pluginManager.registerEvents(EntityDamage(), this)
    }

    override fun onDisable() {
    }

}
