package com.tim.secrets.web.decrypt

sealed class DecryptionResponse
object NotFound: DecryptionResponse()
object DecryptionFailure: DecryptionResponse()
data class SuccessfulDecryption(val result: String): DecryptionResponse()