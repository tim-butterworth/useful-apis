package com.tim.secrets.core.encrypt

import arrow.core.Option
import com.tim.secrets.io.encryption.CipherProvider
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException

class Encryptor(
        private val cipherProvider: CipherProvider
) {
    fun encrypt(token: String, message: String): Option<String> {
        val cipher = cipherProvider.getEncryptCipher(token)

        return try {
            val encrypted = cipher.doFinal(message.toByteArray(Charsets.UTF_8))

            Option.just(Base64.getEncoder().encodeToString(encrypted))
        } catch (exception: IllegalBlockSizeException) {
            Option.empty()
        } catch (exception: BadPaddingException) {
            Option.empty()
        }
    }
}
