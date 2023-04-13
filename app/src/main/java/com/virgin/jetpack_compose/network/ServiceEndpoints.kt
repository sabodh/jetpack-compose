
import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.*

interface ServiceEndpoints {

    @GET("app/CheckLogin.asmx/CheckLoginDetails")
    suspend fun getLoginUser(@Query("username") username: String,
                     @Query("password") password: String): Response<List<Login>>

    @GET("app/VazhCategoryView.asmx/ViewVazhCategory")
    suspend  fun getCategory(): Response<VCategory>

}