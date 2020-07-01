package com.example.sensyneapp.data.remote

import com.example.sensyneapp.data.model.Hospital
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: API): ApiHelper {

    companion object {
        private const val DELIMITER = '¬'
    }

    override fun loadCSV(): Single<List<Hospital>> {
        return Single.create { emitter: SingleEmitter<List<Hospital>> ->
            val responseBody = api.downloadFile().execute()
            if(responseBody.isSuccessful){
                responseBody.body()?.byteStream()?.let {
                    val hospitals = mutableListOf<Hospital>()
                    val reader = BufferedReader(it.reader(Charsets.ISO_8859_1))
                    try {
                        var line: String? = null
                        reader.readLine() // skip header
                        while ({ line = reader.readLine(); line }() != null){
                            val rowList = line?.split(DELIMITER)
                            rowList?.let { row ->
                                hospitals.add(
                                    Hospital(
                                        row[0].toLong(),
                                        row[3],
                                        row[4],
                                        row[6] == "true",
                                        row[7],
                                        row[8],
                                        row[11],
                                        row[12],
                                        row[13],
                                        row[14].toDoubleOrNull(),
                                        row[15].toDoubleOrNull(),
                                        row[17],
                                        row[18],
                                        row[19],
                                        row[20]
                                    )
                                )
                            }
                        }

                        if (!emitter.isDisposed) {
                            emitter.onSuccess(hospitals)
                        }
                    } catch (e: IOException){
                        if(!emitter.isDisposed){
                            emitter.onError(e)
                        }
                    } finally {
                        try {
                            it.close()
                        } catch (e: IOException){
                            if(!emitter.isDisposed){
                                emitter.onError(e)
                            }
                        }
                    }
                }
            }
        }
    }


}