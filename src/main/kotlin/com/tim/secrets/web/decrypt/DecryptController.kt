package com.tim.secrets.web.decrypt

import com.tim.secrets.core.decrypt.Decryption
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.FutureTask

@Controller("decrypt")
class DecryptController(
        private val handlerProvider: DecryptionProvider<HttpResponse<DecryptionResponse>>
) {

    private val decryption: Decryption<HttpResponse<DecryptionResponse>> = handlerProvider.getInstance()

    @Post
    fun decryptForKey(request: DecryptionRequest): Single<HttpResponse<DecryptionResponse>> {
        val futureTask = FutureTask {
            decryption.handleRequest(request)
        }
        return Single.fromFuture(futureTask, Schedulers.computation())
                .doOnSubscribe { futureTask.run() }
    }
}