package dependencies

/**
 * Created at Tito on 3/13/19
 *
 * Class that contains common configurations used allover the application like staging and
 * productions base apis.
 *
 */

@Suppress("unused")
object Config {
    const val PROD_API_BASE_URL = "https://api.themoviedb.org/"
    const val STAGING_API_BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "fc47660226072874be57974ff797a0cd"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w200/"
}