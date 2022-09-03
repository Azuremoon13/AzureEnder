package xyz.azuremoon.manager

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import xyz.azuremoon.util.LogTrans
//import xyz.azuremoon.manager.openPlayersChest

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.ChatColor
import org.bukkit.inventory.InventoryView
import xyz.azuremoon.AzureEnder
import xyz.azuremoon.util.ConfigController


object CommandController {

    private enum class Commands {
        AEC,
        AECADMIN,
        AECOP;


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
            Commands.AEC -> OpenEnderChest(sender)
            Commands.AECADMIN -> AdminOpen(sender, args)
            Commands.AECOP -> OpOpen(sender, args)
            else -> false
        }
    }

    private fun OpenEnderChest(sender: CommandSender): Boolean {
        if (sender !is Player){
            LogTrans.warn("${sender} is not a player")
            return false;
        }

        var playerExpLvl = sender.level

        if (playerExpLvl < ConfigController.openCost) {
            sender.sendMessage("${ChatColor.RED}Sorry you don't have enough exp to do this.. you need ${ConfigController.openCost} levels")
            return false
        }

        if (ConfigController.openCost != 0) {
            playerExpLvl -= ConfigController.openCost
            sender.setLevel(playerExpLvl)
        }

//        openPlayersChest(sender)
        var playersChest: Inventory = Bukkit.createInventory(null, 27, "PlayersChest")
        playersChest.setContents(sender.getEnderChest().contents)
        sender.openInventory(playersChest)

        return true
    }
    private fun AdminOpen(sender: CommandSender, args: Array<out String>): Boolean {
        if (sender !is Player){
            LogTrans.warn("${sender} is not a player")
            return false;
        }
        val player = args.getPlayer()
            ?: return false; sender.sendMessage("${ChatColor.RED}Sorry ${args} is not a player")

        var playersChestAdmin: Inventory = Bukkit.createInventory(null, 27, "PlayersChestAdmin")

        playersChestAdmin.setContents(player.getEnderChest().contents)

        sender.openInventory(playersChestAdmin)

        return true
    }

    private fun OpOpen(sender: CommandSender, args: Array<out String>): Boolean {
        if (sender !is Player){
            LogTrans.warn("${sender} is not a player")
            return false;
        }
        val player = args.getPlayer()
            ?: return false; sender.sendMessage("${ChatColor.RED}Sorry ${args} is not a player")

        var playersChestOp: Inventory = Bukkit.createInventory(null, 27, "PlayersChestOp")

        playersChestOp.setContents(player.getEnderChest().contents)

        sender.openInventory(playersChestOp)

        return true
    }
    private fun Array<out String>.getPlayer(): Player? {
        val server = AzureEnder.instance
            ?.server
            ?: return null
        return getOrNull(0)
            ?.let { server.getPlayerExact(it) }
    }
}
