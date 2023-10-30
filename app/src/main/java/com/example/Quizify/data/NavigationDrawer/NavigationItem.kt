import com.example.Quizify.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Dashboard : NavigationItem("DashBoard", R.drawable.baseline_color_lens_24, "Dashboard")
    object Settings : NavigationItem("settings", R.drawable.settings, "Settings")
    object Favourite : NavigationItem("about", R.drawable.info, "About")
}