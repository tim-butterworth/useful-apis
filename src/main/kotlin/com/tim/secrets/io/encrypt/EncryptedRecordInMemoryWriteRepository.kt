package com.tim.secrets.io.encrypt

import com.tim.secrets.core.decrypt.EncryptedRecord
import com.tim.secrets.core.encrypt.EncryptedRecordWriteRepository
import com.tim.secrets.core.encrypt.SaveResult
import com.tim.secrets.core.encrypt.SuccessSaving
import com.tim.secrets.io.MapProvider
import javax.inject.Singleton

@Singleton
class EncryptedRecordInMemoryWriteRepository(
        mapProvider: MapProvider
) : EncryptedRecordWriteRepository {

    private val map: MutableMap<String, EncryptedRecord> = mapProvider.getInstance()

    override fun <T> save(
            key: String,
            encryptedRecord: EncryptedRecord,
            completionHandler: (SaveResult) -> T
    ): T {
        map[key] = encryptedRecord

        return completionHandler(SuccessSaving)
    }
}