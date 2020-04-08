package com.tim.secrets.web.tokens

import com.tim.secrets.core.tokens.TokensResponder
import javax.inject.Singleton

@Singleton
class HttpTokensResponder: TokensResponder<SecretTokenResponse> {
    override fun tokenSuccessfullyGenerated(encodedToken: String): SecretTokenResponse {
        return SecretTokenResponse(encodedToken)
    }
}