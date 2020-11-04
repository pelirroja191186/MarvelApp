package com.yhq.marvel.core.utils

import com.yhq.marvel.BuildConfig
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiParameterHandler @Inject constructor() {

    val ts: Long = Date().time
    val publicKey: String = BuildConfig.MARVEL_PUBLIC_KEY
    var hash: String = ""

    init {
        hash = md5(ts.toString() + BuildConfig.MARVEL_PRIVATE_KEY + publicKey)
    }

    private fun md5(stringToHash: String): String {
        val md5 = "MD5"
        try {
            val digest = MessageDigest.getInstance(md5)
            digest.update(stringToHash.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) {
                    h = "0$h"
                }
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}
