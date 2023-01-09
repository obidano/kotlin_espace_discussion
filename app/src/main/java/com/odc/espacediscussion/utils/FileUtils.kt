package com.odc.espacediscussion.utils

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.odc.espacediscussion.R
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


object FileUtils {


    fun readFakeEspaces(context: Context) {
        val resources: Resources = context.resources
        val jsonStream: InputStream = resources.openRawResource(R.raw.fake_espaces)
        val byteArrayOutputStream = ByteArrayOutputStream()

        var ctr: Int
        try {
            ctr = jsonStream.read()
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr)
                ctr = jsonStream.read()
            }
            jsonStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d("", "readFakeEspaces: uri ${byteArrayOutputStream.toString()}")

    }
}