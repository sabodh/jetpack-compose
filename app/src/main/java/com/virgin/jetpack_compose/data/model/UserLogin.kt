 
//https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-

data class UserLogin(
    val results: List<Login>
)
data class Login(
    val enable: Boolean,
    val group: Int,
    val name: String,
    val passwd: String,
    val success: String,
    val UserId : Int
)