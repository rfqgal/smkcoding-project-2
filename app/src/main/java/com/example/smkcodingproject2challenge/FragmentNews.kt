package com.example.smkcodingproject2challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smkcodingproject2challenge.api.Covid19NewsIndonesiaArticle
import com.example.smkcodingproject2challenge.data.Covid19NewsService
import com.example.smkcodingproject2challenge.data.apiNewsRequest
import com.example.smkcodingproject2challenge.data.httpClient
import com.example.smkcodingproject2challenge.util.dismissLoading
import com.example.smkcodingproject2challenge.util.showLoading
import com.example.smkcodingproject2challenge.util.showToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_covid_19.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNews: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid_19, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetNewsData()
    }

    private fun callApiGetNewsData() {
        showLoading(context!!, srl_news)

        val httpClient = httpClient()
        val apiRequest = apiNewsRequest<Covid19NewsService>(httpClient)

        val call = apiRequest.getNewsData()
        call.enqueue(object : Callback<List<Covid19NewsIndonesiaArticle>> {

            override fun onFailure(call: Call<List<Covid19NewsIndonesiaArticle>>, t: Throwable) {
                dismissLoading(srl_news)
            }

            override fun onResponse(
                call: Call<List<Covid19NewsIndonesiaArticle>>,
                response: Response<List<Covid19NewsIndonesiaArticle>>
            ) {
                dismissLoading(srl_news)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                showNews(response.body()!!)

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

    private fun showNews(newsData: List<Covid19NewsIndonesiaArticle>) {
        rv_news.layoutManager = LinearLayoutManager(context)
        rv_news.adapter = AdapterNews(context!!, newsData) {

            val data = it
            showToast(context!!, data.title)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}