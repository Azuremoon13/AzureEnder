package xyz.azuremoon.util

import xyz.azuremoon.AzureEnder
import org.bukkit.configuration.Configuration

object ConfigController {

    private const val OPEN_COST = "open_cost"
    private const val STACK_COST = "stack_cost"
    private const val ITEM_COST = "item_cost"

    private const val DEFAULT_OPEN_COST = 5
    private const val DEFAULT_STACK_COST = 1
    private const val DEFAULT_ITEM_COST = 1

    private val config: Configuration?
        get() = AzureEnder.instance?.config

    val open_cost: Int
        get() = config?.getInt(OPEN_COST, DEFAULT_OPEN_COST)
            ?: DEFAULT_OPEN_COST

    val stack_cost: Int
        get() = config?.getInt(STACK_COST, DEFAULT_STACK_COST)
            ?: DEFAULT_STACK_COST

    val item_cost: Int
        get() = config?.getInt(ITEM_COST, DEFAULT_ITEM_COST)
            ?: DEFAULT_ITEM_COST
}