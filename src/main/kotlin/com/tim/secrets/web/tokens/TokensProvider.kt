package com.tim.secrets.web.tokens

import com.tim.secrets.core.tokens.Tokens
import javax.inject.Singleton

@Singleton
class TokensProvider {
    fun <T> getInstance(): Tokens<T> = Tokens()
}
