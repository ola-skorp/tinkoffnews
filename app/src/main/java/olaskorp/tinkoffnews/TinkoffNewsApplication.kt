package olaskorp.tinkoffnews

import android.app.Application
import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog

/**
 * Created by tery on
 * 06.03.2018.
 */
class TinkoffNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        RealmLog.setLevel(LogLevel.ALL)
    }
}