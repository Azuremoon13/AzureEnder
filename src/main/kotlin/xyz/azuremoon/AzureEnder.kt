package xyz.azuremoon

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import xyz.azuremoon.manager.CommandController
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused") // Class is loaded by plugin manager
class AzureEnder : JavaPlugin() {

    companion object{

        var instance: AzureEnder? = null
            private set
    }

    override fun onEnable() {
        instance = this
        logger.info("Hello, World!")
        saveDefaultConfig()
    }

    override fun onDisable() {
        instance = null
        logger.info("Goodbye, World!")
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (!command.testPermission(sender)) { return false }
        return CommandController.Commandroute(sender, command, args)
    }
}