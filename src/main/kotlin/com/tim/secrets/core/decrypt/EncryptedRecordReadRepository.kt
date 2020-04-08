package com.tim.secrets.core.decrypt

import arrow.core.Option

interface EncryptedRecordReadRepository {
    fun <T> findByKey(key: String, completion: (Option<EncryptedRecord>) -> T): T
}

data class EncryptedRecord(val encryptedString: String)
