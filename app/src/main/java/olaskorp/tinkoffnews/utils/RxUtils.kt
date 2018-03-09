package olaskorp.tinkoffnews.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by
 * tery on 06.03.2018.
 */

fun <Type> Single<Type>.schedulerSubIoObMain(): Single<Type> =
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())