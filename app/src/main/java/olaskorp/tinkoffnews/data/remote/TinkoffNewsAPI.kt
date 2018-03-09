package olaskorp.tinkoffnews.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by
 * tery on 04.03.2018.
 */
interface TinkoffNewsAPI {
    @GET("v1/news")
    fun getNews(): Single<NewsTitleRemoteModel>

    @GET("v1/news_content")
    fun getContent(@Query("id") id: String): Single<NewsContentRemoteModel>
}