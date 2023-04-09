
import com.google.gson.JsonArray
import com.virgin.jetpack_compose.model.UserList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ServiceEndpoints {

    @GET("app/CheckLogin.asmx/CheckLoginDetails")
    suspend fun getLoginUser(@Query("username") username: String,
                     @Query("password") password: String): Response<List<Login>>


}