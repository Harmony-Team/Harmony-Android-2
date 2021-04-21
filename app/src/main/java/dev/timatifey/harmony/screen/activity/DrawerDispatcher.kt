package dev.timatifey.harmony.screen.activity

interface DrawerDispatcher {
    fun lockDrawer()
    fun unlockDrawer()
    fun openDrawer()
    fun closeDrawer()
}