package com.tim.secrets.io.decrypt

import arrow.core.Option
import com.tim.secrets.core.decrypt.EncryptedRecord
import com.tim.secrets.core.decrypt.EncryptedRecordReadRepository
import com.tim.secrets.io.MapProvider
import javax.inject.Singleton

@Singleton
class EncryptedRecordInMemoryReadReadRepository(
        private val mapProvider: MapProvider
) : EncryptedRecordReadRepository {

    private val encryptedMap: Map<String, EncryptedRecord> = mapProvider.getInstance()

    override fun <T> findByKey(
            key: String,
            completion: (Option<EncryptedRecord>) -> T
    ): T = completion(Option.fromNullable(encryptedMap[key]))
}