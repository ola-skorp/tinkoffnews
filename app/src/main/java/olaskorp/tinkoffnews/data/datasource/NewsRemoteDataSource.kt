package olaskorp.tinkoffnews.data.datasource

import io.reactivex.Single
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel
import olaskorp.tinkoffnews.data.remote.ContentRemoteModel
import olaskorp.tinkoffnews.data.remote.TinkoffNewsAPI
import olaskorp.tinkoffnews.data.remote.TitleRemoteModel
import olaskorp.tinkoffnews.mappers.Mapper
import retrofit2.Retrofit

/**
 * Created by
 * tery on 04.03.2018.
 */
class NewsRemoteDataSource(private val retrofit: Retrofit,
                           private val titleMapper: Mapper<TitleRemoteModel, TitleCacheModel>,
                           private val contentMapper: Mapper<ContentRemoteModel, ContentCacheModel>) : NewsDataSource {
    override fun get(): Single<List<TitleCacheModel>> =
            this.retrofit
                    .create(TinkoffNewsAPI::class.java)
                    .getNews()
                    .map { it.payload.map { this.titleMapper.map(it) } }

    override fun getContentById(newsId: String): Single<ContentCacheModel> =
            this.retrofit
                    .create(TinkoffNewsAPI::class.java)
                    .getContent(newsId)
                    .map { this.contentMapper.map(it.payload) }
}