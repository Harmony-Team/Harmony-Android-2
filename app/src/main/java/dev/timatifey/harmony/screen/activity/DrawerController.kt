package dev.timatifey.harmony.screen.activity


interface DrawerController {
    fun lockDrawer()
    fun unlockDrawer()
    fun openDrawer()
    fun closeDrawer()
    fun setDrawerUsername(username: String)
}