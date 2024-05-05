package dev.hideko.nodamagemonster.listener

import dev.hideko.nodamagemonster.NoDamageMonster
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class EntityDamage: Listener {

    @EventHandler
    fun onPlayerDamage(e: EntityDamageByEntityEvent) {
        if(e.damager.type == EntityType.PLAYER) return
        val entities = NoDamageMonster.configure.getStringList("mob")
        if(entities.contains(e.damager.type.toString())) {
            e.isCancelled = true
            e.damage = 0.0
            e.entity.velocity = e.damager.location.direction.multiply(0.5)
        }
    }

}