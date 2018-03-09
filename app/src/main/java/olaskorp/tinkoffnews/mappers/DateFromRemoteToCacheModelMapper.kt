package olaskorp.tinkoffnews.mappers

import olaskorp.tinkoffnews.data.cache.DateCacheModel
import olaskorp.tinkoffnews.data.remote.DateRemoteModel

/**
 * Created by
 * tery on 05.03.2018.
 */
class DateFromRemoteToCacheModelMapper : Mapper<DateRemoteModel, DateCacheModel> {
    override fun map(from: DateRemoteModel?): DateCacheModel {
        if (from == null)
            throw Exception()

        val to = DateCacheModel()
        to.milliseconds = from.milliseconds
        return to
    }
}