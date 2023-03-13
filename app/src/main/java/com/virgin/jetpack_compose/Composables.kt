package com.virgin.jetpack_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp








// list view row item
@Composable
fun ListViewItem(member: TeamMembers) {


    // divided the cell as two parts
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // image logo
        ImageSession()
        // team member details
        ColumText(member = member)
    }

}

@Composable
fun ImageSession() {
    Surface(
        modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .background(Color.White)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)

    }
}

@Composable
fun ColumText(member: TeamMembers) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Name: ${member.name}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Text(
            text = "Age: ${member.age}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
            fontSize = 16.sp
        )
        Text(
            text = "Team: ${member.team}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color.Magenta,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ListHeader(header: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        Text(
            text = "${header}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List() {
    // get all team members
    val memberList = TeamRepo.getTeamDetails()
    // filtering team members using team name
    val teamApple = memberList.filter { it.team == TeamRepo.TEAM_APPLE }
    val teamOrange = memberList.filter { it.team == TeamRepo.TEAM_ORANGE }
    val teamLemon = memberList.filter { it.team == TeamRepo.TEAM_LEMON }

    // rendering list view
    LazyColumn(
        contentPadding = PaddingValues(all = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(Color.LightGray)

    ) {
        // team 1
        // team name as header
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_APPLE)
        }
        // members in particular team
        items(teamApple) { item ->
            ListViewItem(member = item)
        }
        // team 2
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_ORANGE)
        }
        items(teamOrange) { item ->
            ListViewItem(member = item)
        }
        // team 3
        stickyHeader {
            ListHeader(header = TeamRepo.TEAM_LEMON)
        }
        items(teamLemon) { item ->
            ListViewItem(member = item)
        }
    }
}