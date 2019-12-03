package com.epitech.cashmanager.global

import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.nfc.NdefRecord
import android.nfc.NdefMessage
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.AsyncTask
import android.util.Log
import okhttp3.internal.and
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*


class NdefReaderTask : AsyncTask<Tag, Void, String>() {

    override fun doInBackground(vararg params: Tag): String? {
        val tag = params[0]

        val ndef = Ndef.get(tag)
            ?: // NDEF is not supported by this Tag.
            return null

        val ndefMessage = ndef.cachedNdefMessage

        val records = ndefMessage.records
        for (ndefRecord in records) {
            if (ndefRecord.tnf == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(
                    ndefRecord.type,
                    NdefRecord.RTD_TEXT
                )
            ) {
                try {
                    return readText(ndefRecord)
                } catch (e: UnsupportedEncodingException) {
                    Log.e("TAG", "Unsupported Encoding", e)
                }

            }
        }

        return null
    }

    @Throws(UnsupportedEncodingException::class)
    private fun readText(record: NdefRecord): String {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

        val payload = record.payload

        // Get the Text Encoding
        val textEncoding = if (payload[0] and 128 == 0) "UTF-8" else "UTF-16"

        // Get the Language Code
        val languageCodeLength = payload[0] and 51

        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
        // e.g. "en"

        // Get the Text
        return String(
            payload,
            languageCodeLength + 1,
            payload.size - languageCodeLength - 1,
            textEncoding as Charset
        )
    }

    override fun onPostExecute(result: String?) {
        if (result != null) {
            Log.e("result", result.toString())
        }
    }
}