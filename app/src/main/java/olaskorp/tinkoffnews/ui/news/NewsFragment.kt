package olaskorp.tinkoffnews.ui.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.data.cache.TitleCacheModel
import olaskorp.tinkoffnews.data.datasource.NewsCacheDataSource
import olaskorp.tinkoffnews.data.datasource.NewsLocalCacheDataSource
import olaskorp.tinkoffnews.data.datasource.NewsRemoteDataSource
import olaskorp.tinkoffnews.data.repository.NewsDataRepository
import olaskorp.tinkoffnews.mappers.DateFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.mappers.NewsContentFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.mappers.NewsTitleFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.presenter.NewsPresenter
import olaskorp.tinkoffnews.presenter.Presenter
import olaskorp.tinkoffnews.ui.content.ContentFragment

/**
 * Фрагмент со списком новостей
 */
class NewsFragment : Fragment(), INewsView {

    private var adapter: NewsAdapter? = null
    private var presenter: Presenter? = null
    private var srlRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = NewsDataRepository(
                NewsRemoteDataSource(
                        NewsTitleFromRemoteToCacheModelMapper(DateFromRemoteToCacheModelMapper()),
                        NewsContentFromRemoteToCacheModelMapper()),
                NewsCacheDataSource(),
                NewsLocalCacheDataSource())
        this.presenter = NewsPresenter(this, repository)

        val onClickActionListener: (id: String) -> Unit =
                {
                    this.activity.supportFragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                                    android.R.animator.fade_in, android.R.animator.fade_out)
                            .add(R.id.flContainer, ContentFragment.newInstance(it), ContentFragment.TAG)
                            .addToBackStack(ContentFragment.TAG)
                            .commit()
                }
        this.adapter = NewsAdapter(onClickActionListener)

        val rvNews = view.findViewById<RecyclerView>(R.id.rvNews)
        this.srlRefresh = view.findViewById(R.id.srlRefresh)

        this.srlRefresh?.setOnRefreshListener { this.presenter?.update() }

        rvNews.addItemDecoration(DividerItemDecoration(this.context, VERTICAL))
        rvNews.layoutManager = LinearLayoutManager(view.context)
        rvNews.adapter = this.adapter

        this.srlRefresh?.isRefreshing = true
        this.presenter?.update()
    }

    override fun onDestroyView() {
        this.presenter?.destroy()
        super.onDestroyView()
    }

    override fun setNews(news: List<TitleCacheModel>) {
        this.adapter?.setData(news)
        this.srlRefresh?.isRefreshing = false
    }

    override fun setError(errorMessage: Int) {
        this.srlRefresh?.isRefreshing = false
        Toast.makeText(this.context, errorMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): NewsFragment = NewsFragment()

        const val TAG = "NewsFragment"
    }
}
