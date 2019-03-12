package hirez

import com.google.gson.Gson
import hirez.exceptions.ResponseException
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import java.io.IOException
import kotlin.reflect.KClass

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
class Http(
			private val gson: Gson = Gson(),
			userAgent: String
) {
	private val log = LoggerFactory.getLogger(javaClass)
	
	private val http = OkHttpClient.Builder()
				.apply {
					addInterceptor(
								HttpLoggingInterceptor(log::debug)
											.setLevel(HttpLoggingInterceptor.Level.BASIC)
					)
					addInterceptor {
						it.proceed(it.request().newBuilder().addHeader("User-Agent", userAgent).build())
					}
				}
				.build()
	
	fun <T : Any> call(url: String, type: Class<T>): Single<T> = Maybe.create<String> {
		http.newCall(Request.Builder().get().url(url).build())
					.enqueue(object : Callback {
						override fun onFailure(call: Call, e: IOException) {
							it.onError(e)
						}
						
						override fun onResponse(call: Call, response: Response) {
							if (response.isSuccessful) {
								if (response.body() != null) {
									it.onSuccess(response.body()!!.string())
								}
							} else {
								it.onError(ResponseException(response.code(), response.message()))
							}
						}
					})
	}.flatMapSingle { e ->
		Single.create<T> {
			try {
				it.onSuccess(gson.fromJson(e, type))
			} catch (ex: RuntimeException) {
				it.onError(ex)
			}
		}
	}
	
	fun <T : Any> call(url: String, type: KClass<T>) = call(url, type.java)
	
	inline fun <reified T : Any> call(url: String) = call(url, T::class)
}
