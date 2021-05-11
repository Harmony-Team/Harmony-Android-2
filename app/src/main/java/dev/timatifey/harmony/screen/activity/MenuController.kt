package dev.timatifey.harmony.screen.activity

interface MenuController {
    fun lockMenu()
    fun unlockMenu()
    fun openMenu()
    fun closeMenu()
    fun setMenuUsername(username: String)
    fun setSelected(position: Int)
}