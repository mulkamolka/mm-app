package com.mm.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mm.android.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment() {
    private var _binding:FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        val view = binding.root
        val webView = binding.webview
        val url = arguments?.getString("url")
        Log.d("test2 url", "${url}")

        webView.webViewClient = WebViewClient() // 새 창 띄우지 않기
        webView.webChromeClient = WebChromeClient()

        webView.settings.loadWithOverviewMode = true // 웹 뷰 크기에 맞추도록 설정
        webView.settings.useWideViewPort = true // 웹 뷰 크기에 맞추도록 설정
        webView.loadUrl("${url}")

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}