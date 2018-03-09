package olaskorp.tinkoffnews.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by
 * tery on 04.03.2018.
 */
open class TitleCacheModel : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var text: String? = null
    var publicationDate: DateCacheModel? = null

    companion object {
        const val FIELD_ID = "id"
    }
}