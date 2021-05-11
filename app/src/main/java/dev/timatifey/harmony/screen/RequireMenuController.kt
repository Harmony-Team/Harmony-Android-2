package dev.timatifey.harmony.screen

import dev.timatifey.harmony.screen.activity.MenuController

interface RequireMenuController {
    fun bindMenuController(menuController: MenuController)
}