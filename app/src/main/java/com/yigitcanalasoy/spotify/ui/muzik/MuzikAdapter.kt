package com.yigitcanalasoy.spotify.ui.muzik

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitcanalasoy.spotify.data.model.Muzikler
import com.yigitcanalasoy.spotify.databinding.MuzikCardViewItemBinding
import com.yigitcanalasoy.spotify.utils.ItemClickListener
import com.yigitcanalasoy.spotify.utils.GlideUtil

class MuzikAdapter (

    var muzikler: List<Muzikler>?,
    var onItemClickListener : ItemClickListener,
    var context: Context) : RecyclerView.Adapter<MuzikAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: MuzikCardViewItemBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = MuzikCardViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return muzikler!!.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            with(holder) {

                binding.apply {
                    var glideUtil = GlideUtil()
                    textViewMuzikAdi.text=muzikler?.get(position)?.muzikAdi
                    textViewSarkici.text=muzikler?.get(position)?.sarkici
                    glideUtil.UrldekiResmiAl(muzikler?.get(position)?.resim.toString(),context,imageViewMuzikImage)

                    cardViewMuzik.setOnClickListener { onItemClickListener.onItemClick(position) }


                }
            }
        }

        fun setData(yeniMuzikList: List<Muzikler>?){
            muzikler=yeniMuzikList
        }
    }