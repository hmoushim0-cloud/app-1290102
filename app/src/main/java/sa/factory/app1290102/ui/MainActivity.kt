package sa.factory.app1290102.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sa.factory.app1290102.data.InMemoryAcademicManuscriptAuditRepository
import sa.factory.app1290102.model.AuditWorkMilestone

val BespokeColorScheme = darkColorScheme(
    primary = Color(0xFF0F766E),
    secondary = Color(0xFFD97706),
    tertiary = Color(0xFFD97706),
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = InMemoryAcademicManuscriptAuditRepository()
        val viewModel = AcademicManuscriptAuditViewModel(repository)

        // Mathematical Lock 2: Support start_screen and screen intent extras
        val startScreen = intent?.getStringExtra("start_screen")
            ?: intent?.getStringExtra("screen")
            ?: "home"

        setContent {
            MaterialTheme(colorScheme = BespokeColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(startScreen) }
                    var selectedItem by remember { mutableStateOf<AuditWorkMilestone?>(null) }

                    when (currentScreen) {
                        "details" -> {
                            AcademicManuscriptAuditDetailsScreen(
                                item = selectedItem,
                                onBack = { currentScreen = "home" },
                                onProceedCheckout = { currentScreen = "checkout" }
                            )
                        }
                        "checkout", "tracking" -> {
                            AcademicManuscriptAuditCheckoutScreen(
                                onBack = { currentScreen = "details" },
                                onComplete = { currentScreen = "home" }
                            )
                        }
                        else -> {
                            AcademicManuscriptAuditHomeScreen(
                                viewModel = viewModel,
                                onSelectItem = { item ->
                                    selectedItem = item
                                    currentScreen = "details"
                                },
                                onQuickCheckout = {
                                    currentScreen = "checkout"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
