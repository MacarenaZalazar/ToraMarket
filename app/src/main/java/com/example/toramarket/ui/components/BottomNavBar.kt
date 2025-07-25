package com.example.toramarket.ui.components

import androidx.compose.material.icons.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.toramarket.ui.navigation.*


data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun BottomNavBar(controller: NavHostController) {
    val items = listOf(
        BottomNavItem(
            label = "Inicio",
            route = ProductsScreenRoute::class.qualifiedName ?: "products",
            icon = Icons.Rounded.Home
        ),
        BottomNavItem(
            label = "Carrito",
            route = CartScreenRoute::class.qualifiedName ?: "cart",
            icon = Icons.Rounded.ShoppingCart
        ),
        BottomNavItem(
            label = "Pedidos",
            route = OrdersScreenRoute::class.qualifiedName ?: "orders",
            icon = Icons.Rounded.Receipt
        ),
        BottomNavItem(
            label = "Perfil",
            route = ProfileScreenRoute::class.qualifiedName ?: "profile",
            icon = Icons.Rounded.AccountCircle
        )
    )

    val navBackStackEntry = controller.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        controller.navigate(item.route) {
                            popUpTo(0) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}