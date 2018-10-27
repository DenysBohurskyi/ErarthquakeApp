package com.knacky.earthquake.presentation

interface Presenter<T> {

    fun setView(view: T)

    fun destroy()

}