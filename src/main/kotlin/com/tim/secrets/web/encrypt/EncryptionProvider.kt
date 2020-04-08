package com.tim.secrets.web.encrypt

import com.tim.secrets.core.encrypt.EncryptedRecordWriteRepository
import com.tim.secrets.core.encrypt.Encryption
import com.tim.secrets.core.encrypt.EncryptionResponder
import com.tim.secrets.core.encrypt.Encryptor
import com.tim.secrets.io.encryption.CipherProvider
import javax.inject.Singleton

@Singleton
class EncryptionProvider<T>(
        private val responder: EncryptionResponder<T>,
        private val cipherProvider: CipherProvider,
        private val repo: EncryptedRecordWriteRepository
) {
    fun getInstance(): Encryption<T> = Encryption(
            responder,
            repo,
            Encryptor(
                    cipherProvider
            )
    )
}
