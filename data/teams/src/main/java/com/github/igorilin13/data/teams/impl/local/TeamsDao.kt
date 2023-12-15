package com.github.igorilin13.data.teams.impl.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.github.igorilin13.data.teams.api.Team
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teams: List<TeamEntity>)

    @Query("SELECT * FROM TeamEntity")
    fun getTeams(): Flow<List<TeamEntity>>

    @Query("DELETE FROM TeamEntity")
    suspend fun clear()

    @Transaction
    suspend fun replace(teams: List<Team>) {
        clear()
        insert(teams.map { it.toDbEntity() })
    }
}