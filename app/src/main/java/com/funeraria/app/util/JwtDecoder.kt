package com.funeraria.app.util

import android.util.Base64
import org.json.JSONObject

object JwtDecoder {
    fun getClaims(token: String): JSONObject? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null
            val payload64  = parts[1]
            val bytes      = Base64.decode(payload64, Base64.DEFAULT)
            val jsonString = String(bytes, charset("UTF-8"))
            JSONObject(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
