package com.hellocuriosity.drappula.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = DrappulaDatabase.Schema,
            name = "drappula.db",
        )
}
