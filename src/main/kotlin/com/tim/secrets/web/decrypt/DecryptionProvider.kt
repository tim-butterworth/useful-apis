package com.tim.secrets.web.decrypt

import com.tim.secrets.core.decrypt.Decryption
import com.tim.secrets.core.decrypt.DecryptionResponder
import com.tim.secrets.core.decrypt.Decryptor
import com.tim.secrets.core.decrypt.EncryptedRecordReadRepository
import com.tim.secrets.io.encryption.CipherProvider
import javax.inject.Singleton

@Singleton
class DecryptionProvider<T>(
        private val responder: DecryptionResponder<T>,
        private val cipherProvider: CipherProvider,
        private val repo: EncryptedRecordReadRepository
) {
    fun getInstance(): Decryption<T> = Decryption(
            responder,
            repo,
            Decryptor(
                    cipherProvider
            )
    )
}
