package olaskorp.tinkoffnews.ui.news

import android.support.annotation.StringRes
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 05.03.2018.
 */
interface INewsView {
    fun setNews(news: List<TitleCacheModel>)

    fun setError(@StringRes errorMessage: Int)
}