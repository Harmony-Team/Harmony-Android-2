package dev.timatifey.harmony.screen

import dev.timatifey.harmony.screen.activity.DrawerController

interface RequireDrawerController {
    fun bindDrawerDispatcher(drawer: DrawerController)
}