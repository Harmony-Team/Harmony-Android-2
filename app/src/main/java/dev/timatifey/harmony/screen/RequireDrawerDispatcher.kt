package dev.timatifey.harmony.screen

import dev.timatifey.harmony.screen.activity.DrawerDispatcher

interface RequireDrawerDispatcher {
    fun bindDrawerDispatcher(drawer: DrawerDispatcher)
}