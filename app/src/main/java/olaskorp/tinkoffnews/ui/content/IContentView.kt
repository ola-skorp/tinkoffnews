package olaskorp.tinkoffnews.ui.content

import android.support.annotation.StringRes
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
interface IContentView {
    fun setContent(content: ContentCacheModel)

    fun setTitle(title: TitleCacheModel)

    fun setError(@StringRes errorMessage: Int)
}