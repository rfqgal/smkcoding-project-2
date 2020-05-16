package com.example.smkcodingproject2challenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import com.example.smkcodingproject2challenge.data.Covid19IndonesiaService
import com.example.smkcodingproject2challenge.data.apiRequest
import com.example.smkcodingproject2challenge.data.httpClient
import com.example.smkcodingproject2challenge.util.dismissLoading
import com.example.smkcodingproject2challenge.util.showLoading
import com.example.smkcodingproject2challenge.util.showToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_indonesia.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentIndonesia: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_indonesia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetIndonesiaData()
    }

    private fun callApiGetIndonesiaData() {
        showLoading(context!!, srl_indonesia)

        val httpClient = httpClient()
        val apiRequest = apiRequest<Covid19IndonesiaService>(httpClient)

        val call = apiRequest.getIndonesiaData()
        call.enqueue(object : Callback<List<Covid19IndonesiaItem>> {

            override fun onFailure(call: Call<List<Covid19IndonesiaItem>>, t: Throwable) {
                dismissLoading(srl_indonesia)
            }

            override fun onResponse(
                call: Call<List<Covid19IndonesiaItem>>,
                response: Response<List<Covid19IndonesiaItem>>
            ) {
                dismissLoading(srl_indonesia)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                showIndonesiaData(response.body()!!)

                            else -> {
                                showToast(context!!, "Berhasil")
                            }
                        }

                    else -> {
                        showToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun showIndonesiaData(indonesiaData: List<Covid19IndonesiaItem>) {
        rv_indonesia.layoutManager = LinearLayoutManager(context)
        rv_indonesia.adapter = AdapterIndonesia(context!!, indonesiaData) {

            val data = it
            showToast(context!!, data.negara)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}