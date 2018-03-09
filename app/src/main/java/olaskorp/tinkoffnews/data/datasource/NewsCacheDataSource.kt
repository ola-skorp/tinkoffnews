package olaskorp.tinkoffnews.data.datasource

import io.reactivex.Single
import io.realm.Realm
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
class NewsCacheDataSource : NewsDataSource {
    override fun get(): Single<List<TitleCacheModel>> =
            Single.fromCallable {
                Realm.getDefaultInstance().use { realm ->
                    realm.copyFromRealm(realm.where(TitleCacheModel::class.java).findAll())
                }
            }

    override fun getContentById(newsId: String): Single<ContentCacheModel> =
            Single.fromCallable {
                Realm.getDefaultInstance().use { realm ->
                    realm.where(ContentCacheModel::class.java)
                            .equalTo(ContentCacheModel.FIELD_ID, newsId).findFirst()?.let { realm.copyFromRealm(it) }
                }
            }
}