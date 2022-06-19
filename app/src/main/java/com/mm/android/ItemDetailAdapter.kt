package com.mm.android

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mm.android.databinding.DetailNewsItemRecyclerBinding
import com.mm.android.databinding.FragmentItemDetailGraphBinding

class ItemDetailAdapter : RecyclerView.Adapter<ItemDetailAdapter.Holder>() {
    var detail = ArrayList<Detail>()
    lateinit var context: Context
    lateinit var newsList: MutableList<News>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context

        Log.d("test onCreateViewHolder", "Let's onCreateViewHolder")
        when (viewType) {
            Detail.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_ A ok")
                val binding = FragmentItemDetailGraphBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding)
            }
            Detail.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B ok")
                val binding = DetailNewsItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding, parent)
            }
            else -> {
                Log.i("test MyAdapter", "Unrecognized viewType = $viewType")
                val binding = DetailNewsItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return Holder(binding, parent)
            }
        }

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Log.d("test onBindViewHolder", "Let's onBindViewHolder")
        when (holder.itemViewType) {
            Detail.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_A ${position}")
                holder.setGraph(context)
            }
            Detail.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B${position}")
                val newsData = newsList.get(position)
                holder.setNews(newsData)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("test getItemCount", "Let's getItemCount ${detail.size}")
        return detail.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("test getItemViewType", "Let's getItemViewType ${detail[position].type}")
        return detail[position].type
    }


    inner class Holder : RecyclerView.ViewHolder {
        lateinit var bindingA: FragmentItemDetailGraphBinding
        lateinit var bindingB: DetailNewsItemRecyclerBinding
        lateinit var context: Context

        constructor(bindingA: FragmentItemDetailGraphBinding) : super(bindingA.root) {
            this.bindingA = bindingA
        }

        // intent 구현을 위해 parent를 인자로 받음
        constructor(
            bindingB: DetailNewsItemRecyclerBinding,
            parent: ViewGroup
        ) : super(bindingB.root) {
            this.bindingB = bindingB
            this.context = parent.context

            bindingB.root.setOnClickListener {
                val intent = Intent(context, NewsDetailActivity::class.java)

                val title = newsList.get(adapterPosition).title
                val content = newsList.get(adapterPosition).content
                val url = newsList.get(adapterPosition).url
                intent.putExtra("title", "${title}")
                intent.putExtra("content", "${content}")
                intent.putExtra("url", "${url}")
                context.startActivity(intent)
            }
        }

        fun setGraph(context: Context) {
            Log.d("test setGraph", "setGraph ok")
            bindingA.text3.text = "This is graph"

            // 차트 지정
            var lineChart = bindingA.chart

            // 데이터 입력
            var graphData = ArrayList<Entry>()
            for (i in 0..100) {
//            val localDate = LocalDate.now().minusDays(i.toLong())
                val i = i.toFloat()
                val price = Math.random() * 100
                graphData.add(Entry(i, price.toFloat()))
            }

            // 그래프 생성 - 라인 생성하기
            val lineDataSet = LineDataSet(graphData, "속성1")
            lineDataSet.lineWidth = 5f // Line Width 설정
            lineDataSet.color = Color.argb(100, 8, 192, 217) // Line Color 설정
            lineDataSet.circleRadius = 5f
            lineDataSet.setCircleColor(Color.rgb(8, 192, 217)) // Line Circle Color 설정
            lineDataSet.circleHoleColor = Color.rgb(255, 255, 255) // Line Hole Circle Color 설정
            lineDataSet.setDrawFilled(false) // 그래프 하단 색상 채우기
            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

            // 그래프 생성 - 여러개의 라인 담아두기
            val lineData = LineData()
            lineData.addDataSet(lineDataSet) // 생성한 라인 넣기
            lineData.setValueTextColor(Color.argb(80, 0, 0, 0)) // 라인 데이터 텍스트 컬러 설정
            lineData.setValueTextSize(10F) // 텍스트 크기 설정

            // X축 설정
            val xAxis = lineChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM // X축 표시에 대한 위치 설정
            xAxis.valueFormatter = null // X축 데이터 재 가공
            xAxis.textColor = Color.BLACK // X축 텍스트컬러설정
            xAxis.gridColor = Color.BLACK // X축 줄의 컬러 설정
            xAxis.setDrawGridLines(false) // X축 gride 노출 설정
            xAxis.setDrawAxisLine(false) // X축 노출 설정
            xAxis.granularity = 1f // X축 레이블 표시 간격

            // Y축 설정 - 좌측
            val yAxisLeft = lineChart.axisLeft // Y축 왼쪽면 설정
            yAxisLeft.valueFormatter = null // Y축 데이터 재 가공
            yAxisLeft.textColor = Color.BLACK // Y축 텍스트컬러설정
            yAxisLeft.gridColor = Color.BLACK // Y축 줄의 컬러 설정
            yAxisLeft.setDrawGridLines(false) // Y축 gride 노출 설정
            yAxisLeft.setDrawAxisLine(false)  // Y축 노출 설정

            // Y축 설정 - 우측, 비활성화
            val yAxisRight = lineChart.axisRight //Y축의 오른쪽면 설정
            yAxisRight.setDrawLabels(false)
            yAxisRight.setDrawAxisLine(false)
            yAxisRight.setDrawGridLines(false)

            // 차트 설정
            lineChart.data = lineData
            lineChart.description = null // 차트 Description 설정
            lineChart.setPinchZoom(false) // 확대 설정
            lineChart.isDoubleTapToZoomEnabled = false // 더블탭 설정
            lineChart.moveViewToX(100f) // 그래프 시작 위치
            lineChart.setVisibleXRangeMaximum(5f) // 그래프 데이터 표시 갯수

            // 레전드 설정
            val legend = lineChart.legend
            legend.textColor = Color.RED

            // 데이터 주입
            lineChart.data = lineData

            // 차트 생성
            lineChart.invalidate()
        }

        fun setNews(news: News) {
            Log.d("test setNews", "setNews ok")
            Glide.with(itemView.context).load("${news.image}").centerCrop().into(bindingB.imageView)
            bindingB.textView2.text = "${news.time}"
            bindingB.textView3.text = "${news.newsPaper}"
            bindingB.textView4.text = "${news.title}"
            bindingB.textView5.text = "${news.content}"
        }

    }
}