package com.tim.secrets.core.encrypt

interface EncryptionResponder<T> {
    fun success(): T
    fun error(): T
    fun encryptionError(): T
}
