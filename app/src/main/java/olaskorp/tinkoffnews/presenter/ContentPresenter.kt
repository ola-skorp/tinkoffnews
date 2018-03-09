package olaskorp.tinkoffnews.presenter

import io.reactivex.disposables.Disposable
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.data.repository.NewsRepository
import olaskorp.tinkoffnews.ui.content.IContentView
import olaskorp.tinkoffnews.utils.schedulerSubIoObMain

/**
 * Created by
 * tery on 05.03.2018.
 */
class ContentPresenter(private val newsId: String,
                       private val iView: IContentView,
                       private val repository: NewsRepository) : Presenter {

    private var disposableGetTitle: Disposable? = null
    private var disposableGetContent: Disposable? = null
    private var disposableUpdate: Disposable? = null

    override fun update() {
        this.disposableUpdate = this.updateContent()
    }

    override fun destroy() {
        this.disposableUpdate?.let { if (!it.isDisposed) it.dispose() }
        this.disposableGetContent?.let { if (!it.isDisposed) it.dispose() }
    }

    private fun error(throwable: Throwable) {
        throwable.printStackTrace()
        this.iView.setError(R.string.error)
    }

    private fun updateContent(): Disposable =
            this.repository.updateContent(this.newsId).schedulerSubIoObMain().subscribe({
                this.disposableGetContent = this.getContent()
                this.disposableGetTitle = this.getTitle()
            }, { this.error(it) })

    private fun getContent(): Disposable =
            this.repository.getContent(this.newsId).schedulerSubIoObMain().subscribe({ this.iView.setContent(it) }, { this.error(it) })

    private fun getTitle(): Disposable =
            this.repository.getTitle(this.newsId).schedulerSubIoObMain().subscribe({ this.iView.setTitle(it) }, { this.error(it) })
}