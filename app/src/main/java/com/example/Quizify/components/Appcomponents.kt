@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.Quizify.components

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Quizify.navigation.Quizapprouter
import com.example.Quizify.navigation.Screen
import com.example.Quizify.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = Color.White
        ),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(labelValue: String, painterResource: Painter, onTextSelected: (String) -> Unit, errorStatus:Boolean=false){
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F8F8)),
        label= { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF92A3FD),
            focusedLabelColor = Color(0xFF92A3FD),
            cursorColor = Color(0xFF92A3FD)
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue,
        onValueChange = {
            textValue=it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFields(labelValue: String,painterResource: Painter, onTextSelected: (String) -> Unit, errorStatus:Boolean){
    var password by remember {
        mutableStateOf("")
    }
    val passwordvisibility = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F8F8)),
        label= { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF92A3FD),
            focusedLabelColor = Color(0xFF92A3FD),
            cursorColor = Color(0xFF92A3FD)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password,
        onValueChange = {
            password=it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
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
        else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckBoxComponent(value: String, onTextSelected:(String)->Unit, onCheckedChange:(Boolean)->Unit){
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkedState= remember {
            mutableStateOf(false)
        }
        Checkbox(checked=checkedState.value,
            onCheckedChange = {
                checkedState.value=!checkedState.value
                onCheckedChange.invoke(it)
            })
        ClickableTextComponent(value = value,onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected:(String)->Unit){
    val initialText="By continuing you accept our "
    val privacyText="Privacy Policy"
    val andText=" and "
    val termandcondition="Terms of Use"

    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style= SpanStyle(color= Color(0xFF92A3FD))){
            pushStringAnnotation(tag=privacyText, annotation = privacyText)
            append(privacyText)
        }
        append(andText)
        withStyle(style= SpanStyle(color= Color(0xFF92A3FD))){
            pushStringAnnotation(tag=termandcondition, annotation = termandcondition)
            append(termandcondition)
        }
    }

    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent","{$span}")

                if(span.item==termandcondition || span.item==privacyText){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun ButtonComponent(value: String, onButtonClicked:()->Unit, isEnabled:Boolean=false){
    Button(
        modifier= Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = { onButtonClicked.invoke() },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape=RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color(0xFF9DCEFF), Color(0xFF92A3FD))),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color(0xFF7B6F72),
            thickness = 1.dp
        )
        Text(text = " or ", fontSize = 17.sp,color= Color(0xFF1D1617))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color(0xFF7B6F72),
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableTextComponent(value1: String,value2:String, onTextSelected:(String)->Unit){
    val initialText=value1
    val loginText=value2

    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style= SpanStyle(color= Color(0xFF92A3FD))){
            pushStringAnnotation(tag=loginText, annotation = loginText)
            append(loginText)
        }
    }


    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableLoginTextComponent","{$span}")

                if(span.item==loginText){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun UnderlinedTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= Color.Gray,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(toolbartitle:String, navigationIconClicked:()->Unit){
    TopAppBar(
        modifier = Modifier.background(Brush.horizontalGradient(colors = listOf(Color(0xFF2EAAFA), Color(0xFF8C04DB)))),
        title = {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment= Alignment.Center
            ){
                Text(text = toolbartitle, color = Color.White,modifier = Modifier.graphicsLayer {
                    translationX = (-16).dp.toPx()
                })
            }},
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu",  tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent )
    )
}

@Composable
fun ProfileEdit(value: String){
    ClickableText(
        text = AnnotatedString(value),
        onClick={Quizapprouter.navigateTo(Screen.Profileactivity)},
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    )
}

@Composable
fun NavigationDrawerHeader(value:String?){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Brush.horizontalGradient(colors = listOf(Color(0xFF2EAAFA), Color(0xFF8C04DB))))
        .height(220.dp)
        .padding(20.dp)
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ProfileImage()
            Spacer(modifier = Modifier.height(2.dp))
            ProfileEdit(value = "Profile")
            HeadingTextComponent(value = value?:"BrainTest")
        }
    }
}

@Composable
fun ProfileImage(){
    Image(modifier = Modifier
        .size(100.dp)
        .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
        .clip(CircleShape),painter = painterResource(id = R.drawable.quiz_app_splash), contentDescription = "Porfile")
}

@Composable
fun NavigationDividerComponent(){
    Row(modifier = Modifier.fillMaxWidth()
    ) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color(0xFF7B6F72),
            thickness = 1.dp
        )
    }
}

@Composable
fun LogoutComponent(onLogoutClicked:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onLogoutClicked()
            }
            .padding(16.dp)
    ) {
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.logout), contentDescription = "Logout")
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = "Logout",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Composable
fun CardComponentMarathon(cardHeight:Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(cardHeight.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Quiz Marathon",
                    fontSize =  24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Take participate to gain knowledge")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "You just participate only just ₹99 and can win excited prices")

                Spacer(modifier = Modifier.height(2.dp))

            }
            Column {

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.size(width = 50.dp, height = 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.quiz_app_splash),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardComponentFullWidth(cardHeight:Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(cardHeight.dp)
            .padding(10.dp),
        onClick = {Quizapprouter.navigateTo(Screen.PracticeSets)},
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Full Test",
                    fontSize =  24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Here you have a full test. It will gives you feel like a real exam.")

                Spacer(modifier = Modifier.height(2.dp))

            }
            Column {

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.size(width = 50.dp, height = 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.quiz_app_splash),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun CardComponentHalfWidth(subjectName:String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(150.dp)
            .padding(10.dp)
            .width(160.dp),onClick = {Quizapprouter.navigateTo(Screen.PracticeSets)},
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(modifier=Modifier.fillMaxWidth(),
                    text = subjectName,
                    fontSize =  18.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerTextField(
    label:String,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val onDateSelectedInternal = { date: Long ->
        val selectedLocalDate = Instant.ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        onDateSelected(selectedLocalDate)
    }

    val selectedDateState by rememberUpdatedState(selectedDate)

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    val dateFormat = remember { SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()) }

    val mDate = remember { mutableStateOf(dateFormat.format(selectedDateState.toEpochDay())) }
    Row{
        var value by remember { mutableStateOf("") }
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = mDate.value,
            onValueChange = {
                mDate.value = it
            },
            readOnly = false,
            label = { Text(label) },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_calendar_month_24), contentDescription = "Calender")},
            trailingIcon = {
                IconButton(
                    onClick = {
                        val datePicker = DatePickerDialog(
                            mContext,
                            { _, year, month, dayOfMonth ->
                                val formattedDate = "${dayOfMonth}-${month + 1}-${year}"
                                mDate.value = formattedDate
                                onDateSelectedInternal(calendar.timeInMillis)
                            },
                            mYear,
                            mMonth,
                            mDay
                        )

                        datePicker.show()
                    }
                ) {
                    Icon(painter= painterResource(id = R.drawable.baseline_edit_calendar_24), contentDescription = "Select Date")
                }
            }
        )
    }
}

@Composable
fun AdminCardComponentMarathon(cardHeight: Int) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(cardHeight.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Quiz Marathon",
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center // Center both horizontally and vertically
            )
        }
    }
}

@Composable
fun AdminFullTestComponent(cardHeight: Int){
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(cardHeight.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Full Test",
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center // Center both horizontally and vertically
            )
        }
    }
}

@Composable
fun AddQuestionCard(){
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(4.dp)){
                Text(
                    text = "Question 1: ",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                TwoLineTextFieldDemo()
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 1: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo()
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 2: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo()
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 3: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo()
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Options 4: ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo()
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Answer:     ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    TwoLineTextFieldDemo()
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun TwoLineTextField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isFocused = false
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isFocused) Color.Blue else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            }
    )
}

@Composable
fun TwoLineTextFieldDemo() {
    var text by remember { mutableStateOf("") }

    TwoLineTextField(
        text = text,
        onTextChanged = {
            text = it
        }
    )
}