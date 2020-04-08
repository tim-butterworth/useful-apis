package com.tim.secrets.web.encrypt

import com.tim.secrets.core.encrypt.EncryptionResponder
import javax.inject.Singleton

@Singleton
class HttpEncryptionResponder : EncryptionResponder<EncryptionResponse> {
    override fun success(): EncryptionResponse = EncryptionSuccess
    override fun error(): EncryptionResponse = EncryptionError
    override fun encryptionError(): EncryptionResponse = EncryptionError
}