package com.tim.secrets.core.tokens

interface TokensResponder<T> {
    fun tokenSuccessfullyGenerated(encodedToken: String): T
}
