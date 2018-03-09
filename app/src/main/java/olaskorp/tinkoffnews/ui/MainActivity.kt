package olaskorp.tinkoffnews.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import olaskorp.tinkoffnews.R
import olaskorp.tinkoffnews.ui.news.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                        android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.flContainer, NewsFragment.newInstance(), NewsFragment.TAG)
                .commit()

        this.findViewById<Toolbar>(R.id.tBar)?.let {
            this.setSupportActionBar(it)
            it.setNavigationOnClickListener { this.onBackPressed() }
        }
    }
}
