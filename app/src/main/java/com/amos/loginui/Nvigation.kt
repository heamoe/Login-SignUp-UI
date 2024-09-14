package com.amos.loginui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.amos.loginui.ui.theme.HomeScreen
import com.amos.loginui.ui.theme.login.LoginScreen
import com.amos.loginui.ui.theme.signup.PolicyScreen
import com.amos.loginui.ui.theme.signup.PrivacyScreen
import com.amos.loginui.ui.theme.signup.SignUpScreen

sealed class Route{
    data class LoginScreen(val name:String = "login"):Route()
    data class SignUpScreen(val name:String = "SignUp"):Route()
    data class PrivacyScreen(val name:String = "Privacy"):Route()
    data class PolicyScreen(val name:String = "Policy"):Route()
    data class HomeScreen(val name:String = "Home"):Route()
}

@Composable
fun myNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = "login_flow"){
        navigation(startDestination =Route.LoginScreen().name , route ="login_flow" ){
            composable(route = Route.LoginScreen().name){
                LoginScreen(
                    onLoginClick = {navHostController.navigate(Route.HomeScreen().name){popUpTo(route = "login_flow")} },
                    onSignUpClick = {navHostController.navigateToSingleTop(Route.SignUpScreen().name)}
                )
            }
            composable(route = Route.SignUpScreen().name){
                SignUpScreen(
                    onLoginClick = {navHostController.navigateToSingleTop(Route.LoginScreen().name)},
                    onSignupClick = {navHostController.navigate(Route.HomeScreen().name){popUpTo("login_flow")} },
                    onPolicyClick = {navHostController.navigate(Route.PolicyScreen().name){launchSingleTop = true} },
                    onPrivacyClick = {navHostController.navigate(Route.PrivacyScreen().name){launchSingleTop = true} }
                )}
            composable(route = Route.PrivacyScreen().name){ PrivacyScreen {navHostController.navigateUp()}}
            composable(route = Route.PolicyScreen().name){ PolicyScreen {navHostController.navigateUp()}}
        }

        composable(route = Route.HomeScreen().name){ HomeScreen() }
    }
}

fun NavController.navigateToSingleTop(route: String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}