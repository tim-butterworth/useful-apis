package com.tim.secrets.web.encrypt

import com.tim.secrets.core.encrypt.Encryption
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.FutureTask

@Controller("/encrypt")
class EncryptController(
        private val encryptionProvider: EncryptionProvider<EncryptionResponse>
) {

    private val encryption: Encryption<EncryptionResponse> = encryptionProvider.getInstance()

    @Post
    fun encrypt(request: EncryptionRequest): Single<EncryptionResponse> {
        val futureTask = FutureTask {
            encryption.handleRequest(request)
        }
        return Single.fromFuture(futureTask, Schedulers.computation())
                .doOnSubscribe { futureTask.run() }
    }
}