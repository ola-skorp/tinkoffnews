package olaskorp.tinkoffnews.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by
 * tery on 05.03.2018.
 */
open class ContentCacheModel : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var content: String? = null

    companion object {
        const val FIELD_ID = "id"
    }
}