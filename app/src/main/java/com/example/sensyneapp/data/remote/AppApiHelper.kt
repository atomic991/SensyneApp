package com.example.sensyneapp.data.remote

import com.example.sensyneapp.data.model.Hospital
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: API): ApiHelper{

    companion object {
        private const val DELIMITER = "\uFFFD"
    }

    override fun loadCSV(): Single<List<Hospital>> {
        return Single.create { emitter: SingleEmitter<List<Hospital>> ->
            val responseBody = api.downloadFile().execute()
            if(responseBody.isSuccessful){
                responseBody.body()?.byteStream()?.let {
                    val hospitals = mutableListOf<Hospital>()
                    val reader = BufferedReader(InputStreamReader(it))
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
                                        row[14].toDouble(),
                                        row[15].toDouble(),
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