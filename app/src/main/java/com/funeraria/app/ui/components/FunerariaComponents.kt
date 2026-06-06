package com.funeraria.app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.funeraria.app.ui.theme.*

// ─── TextField ────────────────────────────────────────────────────────────────
@Composable
fun FunerariaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isError: Boolean = false,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, style = MaterialTheme.typography.labelLarge) },
            placeholder = { Text(placeholder, color = TextFaint, style = MaterialTheme.typography.bodyLarge) },
            isError = isError,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            enabled = enabled,
            singleLine = singleLine,
            maxLines = maxLines,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor      = PrimaryPurple,
                focusedLabelColor       = PrimaryPurple,
                cursorColor             = PrimaryPurple,
                unfocusedBorderColor    = Border,
                unfocusedLabelColor     = TextSecondary,
                errorBorderColor        = ErrorColor,
                errorLabelColor         = ErrorColor,
                focusedContainerColor   = SurfaceElevated,
                unfocusedContainerColor = SurfaceElevated,
                focusedTextColor        = TextPrimary,
                unfocusedTextColor      = TextPrimary,
            ),
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                }
            } else trailingIcon
        )
        if (isError && errorMessage != null) {
            Spacer(Modifier.height(4.dp))
            Text(text = errorMessage, color = ErrorColor, style = MaterialTheme.typography.bodySmall)
        }
    }
}

// ─── Buttons ─────────────────────────────────────────────────────────────────
@Composable
fun FunerariaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor         = PrimaryPurple,
            contentColor           = Color.White,
            disabledContainerColor = PrimaryPurple.copy(alpha = 0.4f),
            disabledContentColor   = Color.White.copy(alpha = 0.6f),
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.5.dp)
            Spacer(Modifier.width(10.dp))
        }
        Text(
            text = if (isLoading) "Guardando..." else text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FunerariaOutlinedButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryPurple),
        border = BorderStroke(2.dp, PrimaryPurple),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FunerariaDestructiveButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, isLoading: Boolean = false) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ErrorColor, contentColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        if (isLoading) { CircularProgressIndicator(color = Color.White, modifier = Modifier.size(18.dp), strokeWidth = 2.dp); Spacer(Modifier.width(8.dp)) }
        Text(text = if (isLoading) "Eliminando..." else text, fontWeight = FontWeight.Bold)
    }
}

// ─── Loading ──────────────────────────────────────────────────────────────────
@Composable
fun LoadingScreen(message: String = "Cargando...") {
    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = PrimaryPurple, strokeWidth = 3.dp, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(20.dp))
            Text(text = message, color = TextSecondary, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }
    }
}

// ─── Error ────────────────────────────────────────────────────────────────────
@Composable
fun ErrorScreen(message: String, onRetry: (() -> Unit)? = null) {
    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
            Icon(Icons.Default.ErrorOutline, null, tint = ErrorColor, modifier = Modifier.size(64.dp))
            Spacer(Modifier.height(16.dp))
            Text("Algo salió mal", color = TextPrimary, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(8.dp))
            Text(message, color = TextSecondary, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
            if (onRetry != null) {
                Spacer(Modifier.height(28.dp))
                FunerariaButton(text = "Reintentar", onClick = onRetry, modifier = Modifier.width(200.dp))
            }
        }
    }
}

// ─── Empty ────────────────────────────────────────────────────────────────────
@Composable
fun EmptyState(message: String = "No hay registros", icon: ImageVector = Icons.Default.Inbox) {
    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
            Icon(icon, null, tint = TextTertiary, modifier = Modifier.size(64.dp))
            Spacer(Modifier.height(16.dp))
            Text(message, color = TextSecondary, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        }
    }
}

// ─── Confirm Dialog ───────────────────────────────────────────────────────────
@Composable
fun ConfirmDeleteDialog(
    itemName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = SurfaceElevated,
        title = { Text("Eliminar registro", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text  = { Text("¿Estás seguro de que deseas eliminar \"$itemName\"? Esta acción no se puede deshacer.", color = TextSecondary) },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Eliminar", color = ErrorColor, fontWeight = FontWeight.Bold) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar", color = TextSecondary) }
        }
    )
}

// ─── Search Bar ───────────────────────────────────────────────────────────────
@Composable
fun FunerariaSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Buscar...",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(placeholder, color = TextTertiary) },
        leadingIcon = { Icon(Icons.Default.Search, null, tint = TextSecondary, modifier = Modifier.size(20.dp)) },
        trailingIcon = {
            if (query.isNotEmpty()) IconButton(onClick = { onQueryChange("") }) {
                Icon(Icons.Default.Clear, null, tint = TextSecondary, modifier = Modifier.size(18.dp))
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor      = PrimaryPurple,
            unfocusedBorderColor    = Border,
            focusedContainerColor   = SurfaceElevated,
            unfocusedContainerColor = SurfaceElevated,
            focusedTextColor        = TextPrimary,
            unfocusedTextColor      = TextPrimary,
            cursorColor             = PrimaryPurple,
        )
    )
}

// ─── Detail Field Row ─────────────────────────────────────────────────────────
@Composable
fun DetailField(label: String, value: String?, icon: ImageVector? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.Top
    ) {
        if (icon != null) {
            Icon(icon, null, tint = PrimaryPurple, modifier = Modifier.size(20.dp).padding(top = 2.dp))
            Spacer(Modifier.width(12.dp))
        }
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = TextTertiary, letterSpacing = 0.5.sp)
            Spacer(Modifier.height(2.dp))
            Text(value ?: "—", style = MaterialTheme.typography.bodyLarge, color = TextPrimary)
        }
    }
}

// ─── Status Badge ─────────────────────────────────────────────────────────────
@Composable
fun StatusBadge(status: String) {
    val (bg, text) = when (status.lowercase()) {
        "pagado"    -> Pair(Success.copy(alpha = 0.2f), Success)
        "pendiente" -> Pair(Warning.copy(alpha = 0.2f), Warning)
        "cancelado" -> Pair(ErrorColor.copy(alpha = 0.2f), ErrorColor)
        else        -> Pair(Info.copy(alpha = 0.2f), Info)
    }
    Surface(color = bg, shape = RoundedCornerShape(20.dp)) {
        Text(status, color = text, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
    }
}
