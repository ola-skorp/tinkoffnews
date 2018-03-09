package olaskorp.tinkoffnews.mappers

import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.remote.ContentRemoteModel

/**
 * Created by
 * tery on 05.03.2018.
 */
class NewsContentFromRemoteToCacheModelMapper : Mapper<ContentRemoteModel, ContentCacheModel> {
    override fun map(from: ContentRemoteModel?): ContentCacheModel {
        if (from == null)
            throw Exception()

        val to = ContentCacheModel()
        to.id = from.title.id ?: ""
        to.content = from.content
        return to
    }
}