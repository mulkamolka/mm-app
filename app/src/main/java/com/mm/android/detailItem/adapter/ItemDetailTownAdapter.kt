package com.mm.android.detailItem.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mm.android.databinding.DetailMarketsItemRecyclerBinding
import com.mm.android.databinding.FragmentItemDetailTownMapBinding
import com.mm.android.detailItem.data.Markets
import com.mm.android.detailItem.data.MultiListType
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class ItemDetailTownAdapter(activity: FragmentActivity?) : RecyclerView.Adapter<ItemDetailTownAdapter.Holder>() {
    var activity = activity
    var multiListType = ArrayList<MultiListType>()
    lateinit var context: Context
    lateinit var marketslist: MutableList<Markets>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context

        Log.d("test onCreateViewHolder", "Let's onCreateViewHolder")
        when (viewType) {
            MultiListType.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_ A ok")
                val binding = FragmentItemDetailTownMapBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding)
            }
            MultiListType.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B ok")
                val binding = DetailMarketsItemRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return Holder(binding, parent)
            }
            else -> {
                Log.i("test MyAdapter", "Unrecognized viewType = $viewType")
                val binding = DetailMarketsItemRecyclerBinding.inflate(
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
            MultiListType.TYPE_A -> {
                Log.d("test onBindViewHolder", "TYPE_A ${position}")
//                holder.setMap(activity!!)
            }
            MultiListType.TYPE_B -> {
                Log.d("test onBindViewHolder", "TYPE_ B${position}")
                val marketsData = marketslist.get(position)
                holder.setMarkets(marketsData)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("test getItemCount", "Let's getItemCount ${multiListType.size}")
        return multiListType.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("test getItemViewType", "Let's getItemViewType ${multiListType[position].type}")
        return multiListType[position].type
    }


    inner class Holder : RecyclerView.ViewHolder {
        lateinit var bindingA: FragmentItemDetailTownMapBinding
        lateinit var bindingB: DetailMarketsItemRecyclerBinding
        lateinit var context: Context

        constructor(bindingA: FragmentItemDetailTownMapBinding) : super(bindingA.root) {
            this.bindingA = bindingA
        }

        // 나중에 intent 구현을 위해 parent를 인자로 받아둠
        constructor(
            bindingB: DetailMarketsItemRecyclerBinding,
            parent: ViewGroup
        ) : super(bindingB.root) {
            this.bindingB = bindingB
        }

        fun setMap(activity: FragmentActivity) {
            var mapView = MapView(activity)
            bindingA.mapView.addView(mapView)
            mapView.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(
                    37.54892296550104,
                    126.99089033876304
                ), true
            );

            val MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304)
            val marker = MapPOIItem()
            marker.itemName = "Default Marker"
            marker.tag = 0
            marker.mapPoint = MARKER_POINT
            marker.markerType = MapPOIItem.MarkerType.BluePin
            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
            mapView.addPOIItem(marker)
        }

        fun setMarkets(markets: Markets) {
            bindingB.textView9.text = "${markets.head}"
            bindingB.textView10.text = "${markets.marketName}"
            bindingB.textView11.text = "${markets.kind}"
            bindingB.textView12.text = "${markets.location}"

            bindingB.textView13.text = "${markets.price}"
            bindingB.textView14.text = "${markets.change}"
//            bindingB.textView15.text = "${markets.upDown}"

            bindingB.textView16.text = "${markets.note}"
            bindingB.textView17.text = "${markets.date}"


        }
    }
}