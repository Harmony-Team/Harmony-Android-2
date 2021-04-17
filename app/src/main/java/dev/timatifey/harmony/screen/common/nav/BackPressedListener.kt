package dev.timatifey.harmony.screen.common.nav

interface BackPressedListener {
    /**
     * @return true if the listener handled the back press, false otherwise
     */
    fun onBackPressed(): Boolean
}


