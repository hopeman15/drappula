package com.hellocuriosity.drappula.db

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDrappulaDatabase(driverFactory: DriverFactory): DrappulaDatabase {
    val driver = driverFactory.createDriver()
    return DrappulaDatabase(driver)
}
