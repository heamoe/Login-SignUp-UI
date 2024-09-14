package com.amos.loginui.ui.theme.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amos.loginui.R
import com.amos.loginui.ui.theme.component.LoginTextField
import com.amos.loginui.ui.theme.component.headerText
import com.amos.loginui.ui.theme.Pink60

val defaultPadding = 16.dp
val itemSpacing = 8.dp


@Composable
fun LoginScreen(onLoginClick:()->Unit,onSignUpClick: () -> Unit){
    val (username, setUsername) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val isFieldEmpty = username.isNotEmpty() && password.isNotEmpty()
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        headerText(
            text = "login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )

        LoginTextField(
            value = username,
            onValueChange =setUsername,
            labelText ="Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(itemSpacing))

        LoginTextField(
            value = password,
            onValueChange =setPassword,
            labelText ="Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checked, onCheckedChange =onCheckedChange )
            Text(text = "Remember me")
            Spacer(modifier = Modifier.width(itemSpacing))
            TextButton(onClick = {}){
                Text(text = "Forgot Password?", color = Pink60)
            }
        }

        Spacer(modifier = Modifier.height(itemSpacing))

        Button(onClick = onLoginClick, enabled = isFieldEmpty, modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(containerColor = Pink60)) {
            Text(text = "Login")
        }

        AlternativeLoginOptions(
            onIconClick = {index ->
                when(index){
                    0->{
                        Toast.makeText(context,"Instagram log in",Toast.LENGTH_SHORT).show()
                    }
                    1->{
                        Toast.makeText(context,"Github log in",Toast.LENGTH_SHORT).show()
                    }
                    2->{
                        Toast.makeText(context,"Googlelog in",Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignUpClick = onSignUpClick

        )
    }
}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index  : Int) -> Unit,
    onSignUpClick: ()->Unit
){
    val iconList = listOf(
        R.drawable.instagram_icon,
        R.drawable.github_icon,
        R.drawable.google_icon
    )
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.BottomCenter),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Or Sign in with")

        Spacer(modifier = Modifier.height(itemSpacing))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconList.forEachIndexed() { index,icon ->
                Icon(painter = painterResource(id = icon),
                    contentDescription =null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onIconClick(index) }
                )
                if (index<(iconList.size-1)){
                    Spacer(modifier = Modifier.width(itemSpacing))
                }
            }
        }


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an Account?")
            TextButton(onClick = onSignUpClick) {
                Text(text = "Sign up", color = Pink60)
            }
        }
        
    }
}