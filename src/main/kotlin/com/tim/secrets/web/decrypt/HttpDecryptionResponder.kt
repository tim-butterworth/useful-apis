package com.tim.secrets.web.decrypt

import com.tim.secrets.core.decrypt.DecryptionResponder
import io.micronaut.http.HttpResponse
import javax.inject.Singleton

@Singleton
class HttpDecryptionResponder: DecryptionResponder<HttpResponse<DecryptionResponse>> {
    override fun invalidKey(): HttpResponse<DecryptionResponse> = HttpResponse.notFound(NotFound)
    override fun failedToDecrypt(): HttpResponse<DecryptionResponse> = HttpResponse.badRequest(DecryptionFailure)
    override fun successfullyDecrypted(result: String): HttpResponse<DecryptionResponse> = HttpResponse.ok(SuccessfulDecryption(result))
}
