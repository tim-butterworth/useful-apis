package com.tim.secrets.core.decrypt

import arrow.core.Option
import com.tim.secrets.io.encryption.CipherProvider
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.spec.SecretKeySpec

class Decryptor(
        private val cipherProvider: CipherProvider
) {
    fun decrypt(token: String, encrypted: String): Option<String> {
        val cipher = cipherProvider.getDecryptCipher(token)

        return try {
            val doFinal = cipher.doFinal(Base64.getDecoder().decode(encrypted))

            Option.just(String(doFinal))
        } catch (exception: IllegalBlockSizeException) {
            Option.empty()
        } catch (exception: BadPaddingException) {
            Option.empty()
        }
    }
}
