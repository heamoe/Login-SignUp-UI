package com.amos.loginui.ui.theme.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amos.loginui.ui.theme.Pink60
import com.amos.loginui.ui.theme.component.LoginTextField
import com.amos.loginui.ui.theme.component.headerText
import com.amos.loginui.ui.theme.login.defaultPadding
import com.amos.loginui.ui.theme.login.itemSpacing


@Composable
fun SignUpScreen(
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPolicyClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    val (firstName, setFirstname) = rememberSaveable {
        mutableStateOf("")
    }
    val (lastName, setLastName) = rememberSaveable {
        mutableStateOf("")
    }
    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (confirmPassword, setConfirmPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (agree, setAgree) = rememberSaveable {
        mutableStateOf(false)
    }
    var isPasswordSame by remember { mutableStateOf(false) }
    val isFieldsNotEmpty =
        firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && agree
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = isPasswordSame) {
            Text(text = "Password is not matching", color = MaterialTheme.colorScheme.error)
        }
        headerText(
            text = "Sign Up",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(
            value = firstName,
            onValueChange = setFirstname,
            labelText = "First Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(
            value = lastName,
            onValueChange = setLastName,
            labelText = "Last Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(
            value = email,
            onValueChange = setEmail,
            labelText = "Email",
            leadingIcon = Icons.Default.Email,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(
            value = confirmPassword,
            onValueChange = setConfirmPassword,
            labelText = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            val privacy = "Privacy"
            val policy = "Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("I Agree With")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = privacy, privacy)
                    append(privacy)
                }
                append(" And ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = policy, policy)
                    append(policy)
                }
            }
            Checkbox(checked = agree, onCheckedChange = setAgree)
            ClickableText(text = annotatedString) { offset ->
                annotatedString.getStringAnnotations(offset, offset).forEach {
                    when (it.tag) {
                        privacy -> {
                            Toast.makeText(context, "Privacy term", Toast.LENGTH_SHORT).show()
                            onPrivacyClick()
                        }

                        policy -> {
                            Toast.makeText(context, "Policy term", Toast.LENGTH_SHORT).show()
                            onPolicyClick()
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(defaultPadding))
        Button(
            onClick = {
                isPasswordSame = password != confirmPassword
                if (!isPasswordSame) {
                    onSignupClick()
                }
            },
            enabled = isFieldsNotEmpty,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Pink60)
        ) {
            Text(text = "Sign Up")
        }
        Spacer(modifier = Modifier.height(itemSpacing))
        val Signin = "Sign In"
        val annotatedString = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                append("Already having an account?")
            }
            append(" ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {

                pushStringAnnotation(tag = Signin, "Sign In")
                append(Signin)
            }

        }
        ClickableText(text = annotatedString) { offset ->
            annotatedString.getStringAnnotations(offset, offset).forEach {
                when (it.tag) {
                    Signin -> {
                        Toast.makeText(context, "Turning to Sign in screen", Toast.LENGTH_SHORT)
                            .show()
                        onLoginClick()
                    }
                }
            }

        }
    }
}