package com.tim.secrets.web.tokens

import com.tim.secrets.core.tokens.TokensResponder
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single

@Controller("/tokens")
class TokensController(
        private val tokensResponder: TokensResponder<SecretTokenResponse>,
        tokensProvider: TokensProvider
) {
    private val tokens = tokensProvider.getInstance<SecretTokenResponse>()

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun createToken(): Single<SecretTokenResponse> {
        return tokens.generate(tokensResponder)
    }
}