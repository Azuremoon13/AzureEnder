package xyz.azuremoon
import xyz.azuremoon.util.ConfigController

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class AecEventListener : Listener {

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        if (e.view.title == "PlayersChestAdmin") {e.isCancelled = true; return}
        if (e.view.title == "PlayersChest") {
            val player = e.whoClicked as Player
            val playerExpLvl = player.level
            if (playerExpLvl < ConfigController.stackCost ) {e.isCancelled = true; return}
            if ((e.rawSlot >= 27 && !e.isShiftClick) || e.currentItem == null) {return}
            player.level = (playerExpLvl-ConfigController.stackCost)
        }
    }

    @EventHandler
    fun onClose(e: InventoryCloseEvent) {
        if (e.view.title == "PlayersChest") {
            val player = e.player as Player
            player.enderChest.setContents(e.view.topInventory.contents)
        }
    }
}