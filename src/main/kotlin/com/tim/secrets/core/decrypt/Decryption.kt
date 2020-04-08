package com.tim.secrets.core.decrypt

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.orElse
import com.tim.secrets.web.decrypt.DecryptionRequest

class Decryption<T>(
        private val responder: DecryptionResponder<T>,
        private val encryptedRecordReadRepository: EncryptedRecordReadRepository,
        private val decryptor: Decryptor
) {
    fun handleRequest(request: DecryptionRequest): T {
        return encryptedRecordReadRepository.findByKey(request.key) { maybeEncryptedRecord ->
            maybeEncryptedRecord
                    .flatMap { record ->
                        decryptor.decrypt(request.token, record.encryptedString)
                                .map { decrypted ->
                                    responder.successfullyDecrypted(decrypted)
                                }
                                .orElse { Option.just(responder.failedToDecrypt()) }
                    }
                    .getOrElse { responder.invalidKey() }
        }
    }
}
