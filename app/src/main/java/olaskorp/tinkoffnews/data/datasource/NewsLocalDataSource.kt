package olaskorp.tinkoffnews.data.datasource

import io.reactivex.Single
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
interface NewsLocalDataSource {
    fun save(news: List<TitleCacheModel>): Single<Boolean>

    fun save(content: ContentCacheModel): Single<Boolean>

    fun getTitle(newsId: String): Single<TitleCacheModel>
}