package com.example.Quizify.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Quizify.R
import com.example.Quizify.components.ButtonComponent
import com.example.Quizify.components.CheckBoxComponent
import com.example.Quizify.components.ClickableTextComponent
import com.example.Quizify.components.DividerTextComponent
import com.example.Quizify.components.HeadingTextComponent
import com.example.Quizify.components.NormalTextComponent
import com.example.Quizify.data.SignupData.SignupUIevents
import com.example.Quizify.data.SignupData.SignupViewModel
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Signupactivity(signupViewModel: SignupViewModel = viewModel()) {
    val state = signupViewModel.signupuistate.value
    val isPrivacyCheckBoxChecked = signupViewModel.isPrivacyCheckBoxChecked.value
    val signupinprogress = signupViewModel.signupinprogress.value
    val emailExistError=signupViewModel.emailError.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.heythere))
                HeadingTextComponent(value = stringResource(id = R.string.createaccount))
                Spacer(modifier = Modifier.height(10.dp))

                var blankError by remember { mutableStateOf(false) }
                var nameError by remember { mutableStateOf(false) }
                var emailError by remember { mutableStateOf(false) }
                var contactError by remember { mutableStateOf(false) }
                var passwordError by remember { mutableStateOf(false) }
                var errorMessage by remember { mutableStateOf("") }
                var checkboxError by remember { mutableStateOf(false) }
                val passwordvisibility = remember {
                    mutableStateOf(false)
                }

                if (blankError) {
                    Text(
                        text = "All fields are mandatory",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                } else if (nameError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                } else if (emailError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                } else if (contactError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                } else if (passwordError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                } else if (checkboxError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }else if(emailExistError){
                    Text(
                        text = "Email already exist",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                // Full Name Input
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F8F8)),
                    value = state.name,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF92A3FD),
                        focusedLabelColor = Color(0xFF92A3FD),
                        cursorColor = Color(0xFF92A3FD)
                    ),
                    keyboardOptions = KeyboardOptions.Default,
                    onValueChange = {
                        signupViewModel.onEvent(SignupUIevents.NameChanged(it))
                        nameError = false
                        blankError = false
                        errorMessage = ""
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.profile), contentDescription = "")
                    },
                    isError = nameError,
                    label = { Text("Full Name") }
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F8F8)),
                    value = state.email,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF92A3FD),
                        focusedLabelColor = Color(0xFF92A3FD),
                        cursorColor = Color(0xFF92A3FD)
                    ),
                    keyboardOptions = KeyboardOptions.Default,
                    onValueChange = {
                        signupViewModel.onEvent(SignupUIevents.EmailChanged(it))
                        emailError = false
                        blankError = false
                        errorMessage = ""
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.email), contentDescription = "")
                    },
                    isError = emailError,
                    label = { Text("Email") }
                )

                // Contact Input
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F8F8)),
                    value = state.phone,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF92A3FD),
                        focusedLabelColor = Color(0xFF92A3FD),
                        cursorColor = Color(0xFF92A3FD)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        signupViewModel.onEvent(SignupUIevents.PhoneChanged(it))
                        contactError = false
                        blankError = false
                        errorMessage = ""
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.phone), contentDescription = "")
                    },
                    isError = contactError,
                    label = { Text("Contact") }
                )

                // Password Input
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F8F8)),
                    label = { Text(text = "Password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF92A3FD),
                        focusedLabelColor = Color(0xFF92A3FD),
                        cursorColor = Color(0xFF92A3FD)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = state.password,
                    onValueChange = {
                        signupViewModel.onEvent(SignupUIevents.PasswordChanged(it))
                        passwordError = false
                        blankError = false
                        errorMessage = ""
                        // Additional validation if needed
                    },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.password), contentDescription = "")
                    },
                    trailingIcon={
                        val iconImage= if(passwordvisibility.value){
                            Icons.Filled.Visibility
                        }else{
                            Icons.Filled.VisibilityOff
                        }

                        val description=if(passwordvisibility.value){
                            "Hide Password"
                        }else{
                            "Show Password"
                        }

                        IconButton(onClick = { passwordvisibility.value=!passwordvisibility.value }) {
                            Icon(imageVector = iconImage, contentDescription = description)
                        }
                    },
                    visualTransformation = if (passwordvisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                CheckBoxComponent(value = "", onTextSelected = {
                    Quizapprouter.navigateTo(Screen.Termsandcondition)
                }, onCheckedChange = {
                    signupViewModel.onEvent(SignupUIevents.PrivacyCheckBoxChanged(it))
                    checkboxError = false
                })

                Spacer(modifier = Modifier.height(60.dp))
                ButtonComponent(value = "Register", onButtonClicked = {
                    if (state.name.isBlank() || state.email.isBlank() || state.phone.isBlank() || state.password.isBlank()) {
                        blankError = true
                    } else if (state.name.length < 3) {
                        nameError = true
                        errorMessage = "Name must have at least 3 characters"
                    } else if (!isEmailValid(state.email)) {
                        emailError = true
                        errorMessage = "Invalid Email"
                    } else if (state.phone.length != 10) {
                        contactError = true
                        errorMessage = "Contact must have 10 digits"
                    } else if (state.password.length < 8) {
                        passwordError = true
                        errorMessage = "Password must have at least 8 characters"
                    } else if (!isPrivacyCheckBoxChecked) {
                        errorMessage = "You must accept the privacy policy"
                        checkboxError = true
                    }

                    if (!blankError && !nameError && !emailError && !contactError && !passwordError && !checkboxError) {
                        signupViewModel.onEvent(SignupUIevents.signupButtonClicked)
                    }
                })

                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableTextComponent(value1 = "Already have an account? ", value2 = "Login Here", onTextSelected = {
                    Quizapprouter.navigateTo(Screen.Loginactivity)
                })
            }
        }

        if (signupinprogress) {
            CircularProgressIndicator()
        }
    }
}

private fun isEmailValid(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}

@Preview
@Composable
fun DefaultPreviewSignupactivity() {
    Signupactivity()
}
