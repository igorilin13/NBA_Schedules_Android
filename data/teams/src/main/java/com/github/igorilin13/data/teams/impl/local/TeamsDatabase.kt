package com.github.igorilin13.data.teams.impl.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.igorilin13.data.teams.impl.local.TeamsDatabase.Companion.DB_VERSION

@Database(
    entities = [TeamEntity::class],
    version = DB_VERSION
)
internal abstract class TeamsDatabase : RoomDatabase() {

    abstract fun teamsDao(): TeamsDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "teams"
    }
}