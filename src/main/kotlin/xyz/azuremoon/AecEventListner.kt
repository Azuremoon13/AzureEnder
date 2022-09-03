package xyz.azuremoon

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.azuremoon.util.ConfigController

internal class AecEventListener : Listener {

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        if (e.view.title == "PlayersChestAdmin")    e.isCancelled = true
        if (e.view.title == "PlayersChest") {
            if (e.rawSlot >= 27) return
            val player = e.whoClicked as Player
            val playerExpLvl = player.level
            if (playerExpLvl < ConfigController.stackCost ) return
            player.level = (playerExpLvl-ConfigController.stackCost)

        }
    }
}