package olaskorp.tinkoffnews.mappers

import olaskorp.tinkoffnews.data.cache.DateCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel
import olaskorp.tinkoffnews.data.remote.DateRemoteModel
import olaskorp.tinkoffnews.data.remote.TitleRemoteModel

/**
 * Created by
 * tery on 05.03.2018.
 */
class NewsTitleFromRemoteToCacheModelMapper(val dateMapper: Mapper<DateRemoteModel, DateCacheModel>) : Mapper<TitleRemoteModel, TitleCacheModel> {
    override fun map(from: TitleRemoteModel?): TitleCacheModel {
        if (from == null)
            throw Exception()

        val to = TitleCacheModel()
        to.id = from.id ?: ""
        to.text = from.text
        to.publicationDate = this.dateMapper.map(from.publicationDate)
        return to
    }
}