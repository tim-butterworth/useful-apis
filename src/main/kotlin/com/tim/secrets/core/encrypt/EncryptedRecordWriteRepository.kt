package com.tim.secrets.core.encrypt

import com.tim.secrets.core.decrypt.EncryptedRecord

interface EncryptedRecordWriteRepository {
    fun <T> save(key: String, encryptedRecord: EncryptedRecord, completionHandler: (SaveResult) -> T): T
}

sealed class SaveResult
object ErrorSaving: SaveResult()
object SuccessSaving: SaveResult()