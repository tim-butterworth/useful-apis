package com.tim.secrets.io.encryption

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Singleton

private enum class CipherMode(val intCode: Int) {
    DECRYPT(Cipher.DECRYPT_MODE), ENCRYPT(Cipher.ENCRYPT_MODE)
}

@Singleton
class CipherProvider {

    private val ALGORITHM: String = "AES"

    private fun getCipher(token: String, mode: CipherMode): Cipher {
        val keyBytes = Base64.getDecoder().decode(token)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(mode.intCode, SecretKeySpec(keyBytes, ALGORITHM))

        return cipher
    }

    fun getDecryptCipher(token: String): Cipher = getCipher(token, CipherMode.DECRYPT)
    fun getEncryptCipher(token: String): Cipher = getCipher(token, CipherMode.ENCRYPT)
}