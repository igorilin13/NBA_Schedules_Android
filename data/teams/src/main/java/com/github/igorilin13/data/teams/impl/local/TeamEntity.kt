package com.github.igorilin13.data.teams.impl.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.igorilin13.data.teams.api.Team

@Entity
internal class TeamEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val fullName: String
)

internal fun TeamEntity.toModel() = Team(
    id = id,
    name = name,
    fullName = fullName
)

internal fun Team.toDbEntity() = TeamEntity(
    id = id,
    name = name,
    fullName = fullName
)