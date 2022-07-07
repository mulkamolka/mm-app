package com.mm.android.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mm.android.searchResult.data.CategoryItemRankListResponse
import com.mm.android.databinding.FragmentHomeCategoryBinding
import com.mm.android.retrofit.RetrofitService
import com.mm.android.retrofit.RetrofitConnection
import com.mm.android.searchResult.*
import com.mm.android.sub.DialogActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeCategoryFragment : Fragment() {
    private var _binding: FragmentHomeCategoryBinding? = null
    private val binding get() = _binding!!
    lateinit var dialog: DialogActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        // 로딩화면 생성
        dialog = DialogActivity(container!!.context)

        binding.agricCategory.setOnClickListener {
            dialog.show()
            val intent = Intent(activity, SearchActivity::class.java)
            getCategoryItemRank(intent, "agric")
        }


        binding.seaCategory.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
//            getCategoryItemRank(intent, "agric")
        }

        binding.livestockCategory.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
//            getCategoryItemRank(intent, "agric")
        }

        binding.emptyCategory.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
//            getCategoryItemRank(intent, "agric")
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCategoryItemRank(intent: Intent, category: String) {
        val retrofitAPI = RetrofitConnection.getInstanceBack().create(RetrofitService::class.java)

        retrofitAPI.getCategoryItemRankData(category)
            .enqueue(object : Callback<CategoryItemRankListResponse> {
                override fun onResponse(
                    call: Call<CategoryItemRankListResponse>,
                    response: Response<CategoryItemRankListResponse>
                ) {
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

                override fun onFailure(call: Call<CategoryItemRankListResponse>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(context, "랭킹 서비스 실행 실패", Toast.LENGTH_LONG).show()
                }
            })
    }

}