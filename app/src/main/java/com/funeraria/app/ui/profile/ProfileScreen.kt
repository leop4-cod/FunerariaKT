package com.funeraria.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.funeraria.app.ui.components.ConfirmDeleteDialog
import com.funeraria.app.ui.theme.*
import com.funeraria.app.ui.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            containerColor = SurfaceElevated,
            title = { Text("Cerrar sesión", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text  = { Text("¿Estás seguro de que deseas cerrar sesión?", color = TextSecondary) },
            confirmButton = {
                TextButton(onClick = { authViewModel.logout(); onLogout() }) {
                    Text("Cerrar sesión", color = ErrorColor, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundColor).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        // Avatar
        Box(
            modifier = Modifier.size(90.dp).clip(CircleShape).background(PrimaryPurple.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentUser?.username?.firstOrNull()?.uppercaseChar()?.toString() ?: "U",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = PrimaryPurple
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = currentUser?.username ?: "Usuario",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = TextPrimary
        )
        Spacer(Modifier.height(6.dp))
        if (currentUser?.isStaff == true) {
            Surface(color = AccentGold.copy(alpha = 0.2f), shape = RoundedCornerShape(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.AdminPanelSettings, null, tint = AccentGold, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Administrador", color = AccentGold, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            Surface(color = PrimaryPurple.copy(alpha = 0.15f), shape = RoundedCornerShape(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Person, null, tint = PrimaryPurple, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Usuario", color = PrimaryPurple, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        // Info Card
        Surface(color = SurfaceColor, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Información de cuenta", style = MaterialTheme.typography.titleSmall,
                    color = TextSecondary, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(16.dp))
                ProfileInfoRow(Icons.Default.Person, "Usuario", currentUser?.username ?: "—")
                HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 12.dp))
                ProfileInfoRow(Icons.Default.Shield, "Rol", if (currentUser?.isStaff == true) "Administrador" else "Usuario estándar")
                HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 12.dp))
                ProfileInfoRow(Icons.Default.Tag, "ID", currentUser?.id?.toString() ?: "—")
            }
        }
        Spacer(Modifier.height(24.dp))
        // About Card
        Surface(color = SurfaceColor, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Acerca de", style = MaterialTheme.typography.titleSmall, color = TextSecondary, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(12.dp))
                ProfileInfoRow(Icons.Default.Info, "Versión", "1.0.0")
                HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 12.dp))
                ProfileInfoRow(Icons.Default.Code, "Backend", "Django REST + PostgreSQL")
            }
        }
        Spacer(Modifier.weight(1f))
        // Logout
        Button(
            onClick = { showLogoutDialog = true },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ErrorColor.copy(alpha = 0.15f), contentColor = ErrorColor),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.Logout, null, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Text("Cerrar Sesión", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun ProfileInfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(icon, null, tint = PrimaryPurple, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = TextTertiary)
            Text(value, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, fontWeight = FontWeight.Medium)
        }
    }
}
