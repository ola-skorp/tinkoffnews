package olaskorp.tinkoffnews.data.cache

import io.realm.RealmObject

/**
 * Created by
 * tery on 04.03.2018.
 */
open class DateCacheModel : RealmObject() {
    var milliseconds: Long? = null
}