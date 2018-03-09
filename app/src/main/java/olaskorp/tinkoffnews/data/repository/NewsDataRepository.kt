package olaskorp.tinkoffnews.data.repository

import io.reactivex.Single
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel
import olaskorp.tinkoffnews.data.datasource.NewsDataSource
import olaskorp.tinkoffnews.data.datasource.NewsLocalDataSource

/**
 * Created by tery on
 * 05.03.2018.
 */
class NewsDataRepository(private val remoteDataSource: NewsDataSource,
                         private val cacheDataSource: NewsDataSource,
                         private val localCacheDataSource: NewsLocalDataSource) : NewsRepository {

    override fun updateTitles(): Single<Boolean> =
            this.remoteDataSource.get().flatMap { this.localCacheDataSource.save(it) }.map { true }

    override fun updateContent(newsId: String): Single<Boolean> =
            this.remoteDataSource.getContentById(newsId).flatMap { this.localCacheDataSource.save(it) }.map { true }

    override fun getTitles(): Single<List<TitleCacheModel>> = this.cacheDataSource.get().map { it.sortedByDescending { it.publicationDate?.milliseconds } }

    override fun getContent(newsId: String): Single<ContentCacheModel> = this.cacheDataSource.getContentById(newsId)

    override fun getTitle(newsId: String): Single<TitleCacheModel> = this.localCacheDataSource.getTitle(newsId)
}