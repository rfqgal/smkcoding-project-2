package com.example.smkcodingproject2challenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smkcodingproject2challenge.api.Covid19GlobalItem
import com.example.smkcodingproject2challenge.data.Covid19GlobalService
import com.example.smkcodingproject2challenge.data.apiRequest
import com.example.smkcodingproject2challenge.data.httpClient
import com.example.smkcodingproject2challenge.util.dismissLoading
import com.example.smkcodingproject2challenge.util.showLoading
import com.example.smkcodingproject2challenge.util.showToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_global.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentGlobal: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_global, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGlobalData()
    }

    private fun callApiGetGlobalData() {
        showLoading(context!!, srl_global)

        val httpClient = httpClient()
        val apiRequest = apiRequest<Covid19GlobalService>(httpClient)

        val call = apiRequest.getGlobalData("https://api.kawalcorona.com")
        call.enqueue(object : Callback<List<Covid19GlobalItem>> {

            override fun onFailure(call: Call<List<Covid19GlobalItem>>, t: Throwable) {
                dismissLoading(srl_global)
            }

            override fun onResponse(
                call: Call<List<Covid19GlobalItem>>,
                response: Response<List<Covid19GlobalItem>>
            ) {
                dismissLoading(srl_global)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                showGlobal(response.body()!!)

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

    private fun showGlobal(globalData: List<Covid19GlobalItem>) {
        rv_global.layoutManager = LinearLayoutManager(context)
        rv_global.adapter = AdapterGlobal(context!!, globalData) {

            val data = it
            showToast(context!!, data.attributes.countryRegion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}