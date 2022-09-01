package xyz.azuremoon.manager

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.azuremoon.manager.CommandController.Commands.AZUREENDER
import xyz.azuremoon.util.LogTrans

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import xyz.azuremoon.util.ConfigController


object CommandController {

    private enum class Commands {
        AZUREENDER;

        companion object {

            fun CommandKey(key: String): Commands? = try {
                valueOf(key.uppercase().replace("-", "_"))
            } catch (e: Exception) {
                null
            }
        }
    }

    fun Commandroute(sender: CommandSender, command: Command, args: Array<out String>): Boolean {
        return when (Commands.CommandKey(command.name)) {
            AZUREENDER -> OpenEnderChest(sender)
            else -> false
        }
    }

    private fun OpenEnderChest(sender: CommandSender): Boolean {
        if (sender !is Player){
            LogTrans.warn("${sender} is not a player")
            return false;
        }
        var player_exp_lvl = sender.level
        println(player_exp_lvl)

        if (player_exp_lvl < ConfigController.open_cost) {
            LogTrans.warn("${sender} doesn't have enough exp!")
            return false
        }

        player_exp_lvl -= ConfigController.open_cost
        println(player_exp_lvl)

        sender.setLevel(player_exp_lvl)
        sender.openInventory(sender.getEnderChest())

        return true
    }
}
