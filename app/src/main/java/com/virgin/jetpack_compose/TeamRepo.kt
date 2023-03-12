package com.virgin.jetpack_compose

object TeamRepo {
    const val TEAM_APPLE = "TEAM APPLE"
    const val TEAM_ORANGE = "TEAM ORANGE"
    const val TEAM_LEMON = "TEAM LEMON"

    fun getTeamDetails(): List<TeamMembers> {
        return listOf(
            TeamMembers("Member 1", 20, TEAM_APPLE),
            TeamMembers("Member 2", 22, TEAM_APPLE),
            TeamMembers("Member 3", 23, TEAM_APPLE),
            TeamMembers("Member 4", 24, TEAM_APPLE),
            TeamMembers("Member 5", 25, TEAM_APPLE),
            TeamMembers("Member 6", 26, TEAM_ORANGE),
            TeamMembers("Member 7", 27, TEAM_ORANGE),
            TeamMembers("Member 8", 28, TEAM_ORANGE),
            TeamMembers("Member 9", 29, TEAM_ORANGE),
            TeamMembers("Member 10", 20, TEAM_ORANGE),
            TeamMembers("Member 11", 21, TEAM_LEMON),
            TeamMembers("Member 12", 22, TEAM_LEMON),
            TeamMembers("Member 13", 23, TEAM_LEMON),
            TeamMembers("Member 14", 24, TEAM_LEMON),
            TeamMembers("Member 15", 25, TEAM_LEMON)

        )
    }
}