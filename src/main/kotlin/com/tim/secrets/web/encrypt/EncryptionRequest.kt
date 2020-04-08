package com.tim.secrets.web.encrypt

data class EncryptionRequest(
        val key: String,
        val token: String,
        val message: String
)