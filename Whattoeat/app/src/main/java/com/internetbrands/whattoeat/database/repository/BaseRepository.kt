package com.internetbrands.whattoeat.database.repository

import kotlinx.coroutines.*
import java.lang.Exception

/**
Created by Wei Lai 2020/4/22
Description:
 */
open class BaseRepository {
    fun <T> execute(
        call: suspend () -> T,
        callback: ((it: T) -> Unit),
        handler: ((e: Exception) -> Unit)? = null
    ): Job =
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val it = call()
                callback.invoke(it)
            } catch (e: Exception) {
               handler?.invoke(e)
            }
        }

    fun <T> execute(call: suspend () -> T): Job =
        GlobalScope.launch(Dispatchers.IO) {
            call()
        }
}