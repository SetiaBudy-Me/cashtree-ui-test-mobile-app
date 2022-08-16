package com.vitiglobal.cashtreeagen.utils

import android.Manifest

@Suppress("unused")
object Constants {
    const val TAG = "SetBud"
    const val TAG_LINE = " "

    const val FILE_PROVIDER_AUTHORITY = "com.xxx.fileprovider"

    const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
    const val TIMEOUT = "timeout"

    const val ENABLED_ALPHA = 1F
    const val DISABLED_ALPHA = .4F

    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)

}
