package com.tim.secrets.web.decrypt

data class DecryptionRequest(
    val key: String,
    val token: String
)