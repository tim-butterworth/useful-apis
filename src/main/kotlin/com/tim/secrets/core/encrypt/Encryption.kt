package com.tim.secrets.core.encrypt

import arrow.core.getOrElse
import com.tim.secrets.core.decrypt.EncryptedRecord
import com.tim.secrets.web.encrypt.EncryptionRequest

class Encryption<T>(
        private val responder: EncryptionResponder<T>,
        private val repo: EncryptedRecordWriteRepository,
        private val encryptor: Encryptor
) {
    fun handleRequest(request: EncryptionRequest): T {
        val encrypted = encryptor.encrypt(request.token, request.message)

        return encrypted.map { encryptedString ->
            repo.save(
                    request.key,
                    EncryptedRecord(encryptedString)
            ) { result ->
                when (result) {
                    is SuccessSaving -> responder.success()
                    is ErrorSaving -> responder.error()
                }
            }
        }.getOrElse { responder.encryptionError() }
    }
}
