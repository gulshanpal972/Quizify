package com.example.Quizify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.Quizify.components.AppToolBar
import com.example.Quizify.components.LogoutComponent
import com.example.Quizify.components.NavigationDividerComponent
import com.example.Quizify.components.NavigationDrawerHeader
import com.example.Quizify.data.NavigationDrawer.Drawer
import com.example.Quizify.data.NavigationDrawer.HomeViewModel
import com.example.Quizify.data.NavigationDrawer.NavigationScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(homeViewModel: HomeViewModel = viewModel()){
    val scaffoldState= rememberScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    val navController = rememberNavController()
    homeViewModel.getUserData()

    Scaffold(scaffoldState=scaffoldState,
        topBar = {
            AppToolBar(toolbartitle = "Quizzify",
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent={
            NavigationDrawerHeader(homeViewModel.emailId.value)
            Drawer(scope = coroutineScope, scaffoldState = scaffoldState, navController = navController)
            NavigationDividerComponent()
            LogoutComponent(onLogoutClicked = { homeViewModel.logout() })
        }
    ) {
            paddingValues ->
        Surface(
            modifier= Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ){
            Column(modifier = Modifier.fillMaxSize()) {
                NavigationScreen(navController = navController)
            }
        }
    }
}