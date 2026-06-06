package com.funeraria.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.funeraria.app.ui.components.FunerariaButton
import com.funeraria.app.ui.components.FunerariaTextField
import com.funeraria.app.ui.theme.*
import com.funeraria.app.ui.viewmodel.AuthUiState
import com.funeraria.app.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading = uiState is AuthUiState.Loading
    val errorMsg  = (uiState as? AuthUiState.Error)?.message

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) onLoginSuccess()
    }

    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor)) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            // ── Hero Header ────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Brush.verticalGradient(HeroGradient)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = PrimaryPurple.copy(alpha = 0.2f),
                        modifier = Modifier.size(80.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("🕊", fontSize = 36.sp)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Funeraria",
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Sistema de Gestión",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AccentGold,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // ── Login Card ────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-32).dp)
                    .padding(horizontal = 24.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = SurfaceColor,
                    tonalElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(28.dp)) {
                        Text(
                            text = "Iniciar Sesión",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Black,
                            color = TextPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Ingresa tus credenciales para continuar",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                        Spacer(Modifier.height(24.dp))

                        // Error
                        if (errorMsg != null) {
                            Surface(
                                color = ErrorColor.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.ErrorOutline, null, tint = ErrorColor, modifier = Modifier.size(20.dp))
                                    Spacer(Modifier.width(10.dp))
                                    Text(errorMsg, color = ErrorColor, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                                }
                            }
                        }

                        FunerariaTextField(
                            value = username,
                            onValueChange = { username = it; viewModel.clearError() },
                            label = "Usuario",
                            placeholder = "Tu nombre de usuario",
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                        Spacer(Modifier.height(16.dp))
                        FunerariaTextField(
                            value = password,
                            onValueChange = { password = it; viewModel.clearError() },
                            label = "Contraseña",
                            placeholder = "Tu contraseña segura",
                            isPassword = true,
                            enabled = !isLoading,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                        Spacer(Modifier.height(28.dp))
                        FunerariaButton(
                            text = "Iniciar Sesión",
                            onClick = { viewModel.login(username.trim(), password) },
                            isLoading = isLoading,
                            enabled = username.isNotBlank() && password.isNotBlank()
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "Sistema de gestión funeraria",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextTertiary,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}
