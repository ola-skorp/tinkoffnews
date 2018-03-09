package olaskorp.tinkoffnews.data.datasource

import io.reactivex.Single
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 04.03.2018.
 */
interface NewsDataSource {
    fun get(): Single<List<TitleCacheModel>>

    fun getContentById(newsId: String): Single<ContentCacheModel>
}