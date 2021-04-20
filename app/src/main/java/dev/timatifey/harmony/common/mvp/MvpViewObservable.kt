package dev.timatifey.harmony.common.mvp

interface MvpViewObservable<ListenerType> : MvpView {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}
