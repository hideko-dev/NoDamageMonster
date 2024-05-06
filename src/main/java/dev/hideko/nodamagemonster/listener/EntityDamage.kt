package dev.hideko.nodamagemonster.listener

import dev.hideko.nodamagemonster.NoDamageMonster
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.projectiles.ProjectileSource

class EntityDamage: Listener {

    @EventHandler
    fun onPlayerDamage(e: EntityDamageByEntityEvent) {
        if(e.damager.type == EntityType.PLAYER) return
        val entities = NoDamageMonster.configure.getStringList("mob")
        if(entities.contains(e.damager.type.toString())) {
            e.damage = 0.0
            e.entity.velocity = e.damager.location.direction.multiply(0.5)
        }
    }

    @EventHandler
    fun onShoot(e: ProjectileHitEvent) {
        if(e.hitEntity is Player) {
            val entities = NoDamageMonster.configure.getStringList("mob")
            if(e.entity.shooter is LivingEntity) {
                val livingType = (e.entity.shooter as LivingEntity).type.toString()
                if(entities.contains(livingType)) {
                    val hitPlayer = e.hitEntity as Player
                    hitPlayer.damage(0.00001, e.entity)
                    val dir = hitPlayer.location.direction
                    val kb = dir.multiply(-0.5)
                    kb.y = 0.5
                    hitPlayer.velocity = kb
                    e.isCancelled = true
                }
            }
        }
    }
}