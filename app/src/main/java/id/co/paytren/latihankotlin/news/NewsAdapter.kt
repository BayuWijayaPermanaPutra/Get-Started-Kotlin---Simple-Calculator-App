package id.co.paytren.latihankotlin.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.co.paytren.latihankotlin.R
import id.co.paytren.latihankotlin.source.dao.News

import kotlinx.android.synthetic.main.adapter_news.view.*

/**
 * Created by BayuWPP on 3/13/18.
 */
class NewsAdapter constructor(context: Context, list: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var context : Context?
    var list: ArrayList<News>?

    init {
        this.context = context
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        /*
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ItemViewHolder(inflater.inflate(R.layout.item_book, parent, false));
         */
        var inflater : LayoutInflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.adapter_news, parent, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        list?.get(position)?.let { news ->
            holder?.bind(news)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.tv_title_news
        val date = itemView.tv_date_news
        val image = itemView.iv_news

        fun bind(news: News) {
            title.setText(news.title)
            date.setText(news.date)
            Glide.with(itemView.context).load(news.image).into(image)
        }
    }
}