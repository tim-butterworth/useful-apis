package com.tim.secrets.web.encrypt

sealed class EncryptionResponse
object EncryptionSuccess: EncryptionResponse()
object EncryptionError: EncryptionResponse()