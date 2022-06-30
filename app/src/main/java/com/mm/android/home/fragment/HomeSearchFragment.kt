package com.mm.android.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mm.android.searchResult.SearchActivity
import com.mm.android.searchResult.data.AllItemRankListResponse
import com.mm.android.databinding.FragmentHomeSearchBinding
import com.mm.android.retrofit.RetrofitService
import com.mm.android.retrofit.RetrofitConnection
import com.mm.android.sub.DialogActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class HomeSearchFragment : Fragment() {
    private var _binding: FragmentHomeSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var dialog: DialogActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        val intent = Intent(activity, SearchActivity::class.java)

        // 로딩화면 생성
        dialog = DialogActivity(container!!.context)

        binding.searchButton.setOnClickListener {
            dialog.show()
            getAllItemRank(intent)
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllItemRank(intent: Intent) {
        val retrofitAPI = RetrofitConnection.getInstance().create(RetrofitService::class.java)

        retrofitAPI.getAllItemRankData()
            .enqueue(object : Callback<AllItemRankListResponse> {
                override fun onResponse(
                    call: Call<AllItemRankListResponse>,
                    response: Response<AllItemRankListResponse>
                ) {dialog.show()
                    if (response.isSuccessful) {
                        Toast.makeText(context, "랭킹 서비스 실행 완료", Toast.LENGTH_LONG).show()
                        val data = response.body()
                        intent.putExtra("itemRankListData", data)
                        dialog.dismiss()
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "랭킹 서비스 실행 실패", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<AllItemRankListResponse>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(context, "랭킹 서비스 실행 실패", Toast.LENGTH_LONG).show()
                }
            })
    }

}