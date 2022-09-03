package xyz.azuremoon.util

import xyz.azuremoon.AzureEnder

object ConfigController {

    private const val OPENCOST = "openCost"
    private const val STACKCOST = "stackCost"
    private const val ITEMCOST = "itemCost"

    private const val DEFAULT_OPEN_COST = 7
    private const val DEFAULT_STACK_COST = 5
    private const val DEFAULT_ITEM_COST = 1

    val openCost: Int
        get() {
            return AzureEnder.instance
                ?.config
                ?.getInt(OPENCOST, DEFAULT_OPEN_COST)
                ?.takeIf { it >= 0 }
                ?: DEFAULT_OPEN_COST
        }

    val stackCost: Int
        get() {
            return AzureEnder.instance
                ?.config
                ?.getInt(STACKCOST, DEFAULT_STACK_COST)
                ?.takeIf { it >= 0 }
                ?: DEFAULT_STACK_COST
        }

    val itemCost: Int
        get() {
            return AzureEnder.instance
                ?.config
                ?.getInt(ITEMCOST, DEFAULT_ITEM_COST)
                ?.takeIf { it >= 0 }
                ?: DEFAULT_ITEM_COST
        }
}