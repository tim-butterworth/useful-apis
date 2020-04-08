package com.tim.secrets.io

import com.tim.secrets.core.decrypt.EncryptedRecord
import javax.inject.Singleton

@Singleton
class MapProvider {

    private val map: MutableMap<String, EncryptedRecord> = mutableMapOf()

    fun getInstance(): MutableMap<String, EncryptedRecord> = map
}
