package olaskorp.tinkoffnews.ui.news

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.data.cache.TitleCacheModel

/**
 * Created by
 * tery on 06.03.2018.
 */
class NewsAdapter(val onClickActionListener: (id: String) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var data = ArrayList<TitleCacheModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun getItemCount(): Int = this.data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = this.data[position].text
    }

    fun setData(news: List<TitleCacheModel>) {
        this.data.clear()
        this.data.addAll(news)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        init {
            itemView.setOnClickListener { if (adapterPosition != NO_POSITION) onClickActionListener.invoke(data[adapterPosition].id) }
        }
    }
}