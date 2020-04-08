package com.tim.secrets.core.decrypt

interface DecryptionResponder<T> {
    fun invalidKey(): T
    fun failedToDecrypt(): T
    fun successfullyDecrypted(result: String): T
}
