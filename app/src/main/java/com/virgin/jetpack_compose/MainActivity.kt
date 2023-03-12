package com.virgin.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Display the list of 3 Team members using list.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            List()
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(){
    // get all team members
    val memberList = TeamRepo.getTeamDetails()
    // filtering team members using team name
    val teamApple = memberList.filter { it.team == TeamRepo.TEAM_APPLE }
    val teamOrange = memberList.filter { it.team == TeamRepo.TEAM_ORANGE }
    val teamLemon = memberList.filter { it.team == TeamRepo.TEAM_LEMON }

    // rendering list view
    LazyColumn(contentPadding = PaddingValues(all = 5.dp),
    verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(Color.LightGray)

    ){
        // team 1
        // team name as header
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_APPLE)
        }
        // members in particular team
        items(teamApple){ item ->
                ListViewItem(member = item)
        }
        // team 2
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_ORANGE)
        }
        items(teamOrange){ item ->
            ListViewItem(member = item)
        }
        // team 3
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_LEMON)
        }
        items(teamLemon){ item ->
            ListViewItem(member = item)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    List()
}