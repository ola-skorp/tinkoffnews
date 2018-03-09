package olaskorp.tinkoffnews.data.datasource

import io.reactivex.Single
import io.realm.Realm
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
class NewsLocalCacheDataSource : NewsLocalDataSource {

    override fun save(content: ContentCacheModel): Single<Boolean> =
            Single.fromCallable {
                Realm.getDefaultInstance().use {
                    it.executeTransaction { it.copyToRealmOrUpdate(content) }
                }
            }.map { true }

    override fun save(news: List<TitleCacheModel>): Single<Boolean> =
            Single.fromCallable {
                Realm.getDefaultInstance().use {
                    it.executeTransaction { it.copyToRealmOrUpdate(news) }
                }
            }.map { true }

    override fun getTitle(newsId: String): Single<TitleCacheModel> =
            Single.fromCallable {
                Realm.getDefaultInstance().use { realm ->
                    realm.where(TitleCacheModel::class.java).equalTo(TitleCacheModel.FIELD_ID, newsId).findFirst()?.let { realm.copyFromRealm(it) }
                }
            }
}