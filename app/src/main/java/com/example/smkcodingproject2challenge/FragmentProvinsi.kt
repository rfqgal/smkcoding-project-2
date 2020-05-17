package com.example.smkcodingproject2challenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smkcodingproject2challenge.api.Covid19ProvinsiItem
import com.example.smkcodingproject2challenge.data.Covid19ProvinsiService
import com.example.smkcodingproject2challenge.data.apiRequest
import com.example.smkcodingproject2challenge.data.httpClient
import com.example.smkcodingproject2challenge.util.dismissLoading
import com.example.smkcodingproject2challenge.util.showLoading
import com.example.smkcodingproject2challenge.util.showToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentProvinsi: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_provinsi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetProvinsiData()
    }

    private fun callApiGetProvinsiData() {
        showLoading(context!!, srl_provinsi)

        val httpClient = httpClient()
        val apiRequest = apiRequest<Covid19ProvinsiService>(httpClient)

        val call = apiRequest.getProvinsiData()
        call.enqueue(object : Callback<List<Covid19ProvinsiItem>> {

            override fun onFailure(call: Call<List<Covid19ProvinsiItem>>, t: Throwable) {
                dismissLoading(srl_provinsi)
            }

            override fun onResponse(
                call: Call<List<Covid19ProvinsiItem>>,
                response: Response<List<Covid19ProvinsiItem>>
            ) {
                dismissLoading(srl_provinsi)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                showProvinsiData(response.body()!!)

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

    private fun showProvinsiData(provinsiData: List<Covid19ProvinsiItem>) {
        rv_provinsi.layoutManager = LinearLayoutManager(context)
        rv_provinsi.adapter = AdapterProvinsi(context!!, provinsiData) {

            val data = it
            showToast(context!!, data.attributes.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}