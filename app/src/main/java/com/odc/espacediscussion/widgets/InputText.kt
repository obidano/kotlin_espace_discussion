package com.odc.espacediscussion.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odc.espacediscussion.utils.fromHex

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputText(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    maxLine: Int = 1,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onImeAction: () -> Unit = {},
    backgroundColor: String = "faedcd",
    textColor: String = "000000",
    imageVector: ImageVector? = Icons.Filled.Edit,
    onTextChanged: (String) -> Unit,
) {
    val keyBoardCtrl = LocalSoftwareKeyboardController.current

    Column(Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(label, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = onTextChanged,
            modifier = modifier
                .fillMaxWidth()
                //.background(color=Color.fromHex("E76F51"))
                .border(width = 0.dp, color = Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                imageVector?.let {
                    Icon(imageVector, "", tint = Color.Blue)
                }
            },
            maxLines = maxLine,
            textStyle = TextStyle(color = Color.fromHex(textColor)),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.fromHex(
                backgroundColor),
//            disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
//            label = { Text(label) },

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = keyBoardType,
            ),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction()
                keyBoardCtrl?.hide()
            })
        )
    }

}
