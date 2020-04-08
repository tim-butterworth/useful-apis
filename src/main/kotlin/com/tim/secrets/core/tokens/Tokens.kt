package com.tim.secrets.core.tokens

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.security.SecureRandom
import java.util.*
import java.util.concurrent.FutureTask
import javax.crypto.KeyGenerator

class Tokens<T> {
    fun generate(tokensResponder: TokensResponder<T>): Single<T> {
        val futureTask = FutureTask {
            val keyGenerator = KeyGenerator.getInstance("AES")

            val secureRandom = SecureRandom()
            val keyBitSize = 256
            keyGenerator.init(keyBitSize, secureRandom)

            val secretKey = keyGenerator.generateKey()

            val encodedToken = Base64.getEncoder().encodeToString(secretKey.encoded)
            tokensResponder.tokenSuccessfullyGenerated(encodedToken)
        }

        return Single
                .fromFuture(futureTask, Schedulers.computation())
                .doOnSubscribe { futureTask.run() }
    }
}
