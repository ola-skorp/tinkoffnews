package olaskorp.tinkoffnews.ui.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.data.cache.ContentCacheModel
import olaskorp.tinkoffnews.data.cache.TitleCacheModel
import olaskorp.tinkoffnews.data.datasource.NewsCacheDataSource
import olaskorp.tinkoffnews.data.datasource.NewsLocalCacheDataSource
import olaskorp.tinkoffnews.data.datasource.NewsRemoteDataSource
import olaskorp.tinkoffnews.data.repository.NewsDataRepository
import olaskorp.tinkoffnews.mappers.DateFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.mappers.NewsContentFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.mappers.NewsTitleFromRemoteToCacheModelMapper
import olaskorp.tinkoffnews.presenter.ContentPresenter
import olaskorp.tinkoffnews.presenter.Presenter
import olaskorp.tinkoffnews.utils.buildDefaultInstance
import retrofit2.Retrofit

class ContentFragment : Fragment(), IContentView {

    private var newsId: String? = null
    private var tvTitle: TextView? = null
    private var tvContent: TextView? = null
    private var presenter: Presenter? = null
    private var srlRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            newsId = arguments.getString(ARG_NEWS_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_content, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (this.activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        val repository = NewsDataRepository(
                NewsRemoteDataSource(
                        Retrofit.Builder().buildDefaultInstance(),
                        NewsTitleFromRemoteToCacheModelMapper(DateFromRemoteToCacheModelMapper()),
                        NewsContentFromRemoteToCacheModelMapper()),
                NewsCacheDataSource(),
                NewsLocalCacheDataSource())
        this.newsId?.let { this.presenter = ContentPresenter(it, this, repository) }
        this.srlRefresh = view.findViewById(R.id.srlRefresh)
        this.srlRefresh?.setOnRefreshListener { this.presenter?.update() }
        this.tvTitle = view.findViewById(R.id.tvTitle)
        this.tvContent = view.findViewById(R.id.tvContent)

        this.srlRefresh?.isRefreshing = true
        this.presenter?.update()
    }

    override fun onDestroyView() {
        this.presenter?.destroy()

        (this.activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setDisplayShowHomeEnabled(false)
        }

        super.onDestroyView()
    }

    override fun setContent(content: ContentCacheModel) {
        this.tvContent?.text = content.content
        this.srlRefresh?.isRefreshing = false
    }

    override fun setTitle(title: TitleCacheModel) {
        this.tvTitle?.text = title.text
    }

    override fun setError(errorMessage: Int) {
        this.srlRefresh?.isRefreshing = false
        Toast.makeText(this.context, errorMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "ContentFragment"
        private const val ARG_NEWS_ID = "newsId"

        fun newInstance(newsId: String): ContentFragment {
            val fragment = ContentFragment()
            val args = Bundle()
            args.putString(ARG_NEWS_ID, newsId)
            fragment.arguments = args
            return fragment
        }
    }
}
