package olaskorp.tinkoffnews.mappers

/**
 * Created by
 * tery on 05.03.2018.
 */
interface Mapper<in TypeFrom, out TypeTo> {
    fun map(from: TypeFrom?): TypeTo
}