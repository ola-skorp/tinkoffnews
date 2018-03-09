package olaskorp.tinkoffnews.data.repository

import io.reactivex.Single
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
interface NewsRepository {

    fun updateTitles(): Single<Boolean>

    fun updateContent(newsId: String): Single<Boolean>

    fun getTitles(): Single<List<TitleCacheModel>>

    fun getContent(newsId: String): Single<ContentCacheModel>

    fun getTitle(newsId: String): Single<TitleCacheModel>
}