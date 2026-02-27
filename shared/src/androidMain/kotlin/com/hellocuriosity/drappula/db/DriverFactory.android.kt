package com.hellocuriosity.drappula.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = DrappulaDatabase.Schema,
            context = context,
            name = "drappula.db",
        )
}
