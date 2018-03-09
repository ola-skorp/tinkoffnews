package olaskorp.tinkoffnews.presenter

import io.reactivex.disposables.Disposable
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.data.repository.NewsRepository
import olaskorp.tinkoffnews.ui.news.INewsView
import olaskorp.tinkoffnews.utils.schedulerSubIoObMain

/**
 * Created by
 * tery on 05.03.2018.
 */
class NewsPresenter(private val iView: INewsView,
                    private val repository: NewsRepository) : Presenter {

    private var disposableGet: Disposable? = null
    private var disposableUpdate: Disposable? = null

    override fun update() {
        this.disposableUpdate = this.updateTitles()
    }

    override fun destroy() {
        this.disposableUpdate?.let { if (!it.isDisposed) it.dispose() }
        this.disposableGet?.let { if (!it.isDisposed) it.dispose() }
    }

    private fun error(throwable: Throwable) {
        throwable.printStackTrace()
        this.iView.setError(R.string.error)
    }

    private fun updateTitles(): Disposable =
            this.repository.updateTitles().schedulerSubIoObMain().subscribe({ this.disposableGet = this.getTitles() }, { this.error(it) })

    private fun getTitles(): Disposable =
            this.repository.getTitles().schedulerSubIoObMain().subscribe({ this.iView.setNews(it) }, { this.error(it) })
}