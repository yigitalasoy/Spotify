package com.yigitcanalasoy.spotify.ui.kategori

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yigitcanalasoy.spotify.data.model.KategoriResponseItem
import com.yigitcanalasoy.spotify.databinding.KategoriCardViewItemBinding
import com.yigitcanalasoy.spotify.utils.GlideUtil
import com.yigitcanalasoy.spotify.utils.ItemClickListener

class KategoriAdapter (

    var kategoriler: List<KategoriResponseItem>?,
    var onItemClickListener : ItemClickListener,
    var context : Context) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: KategoriCardViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = KategoriCardViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return kategoriler!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {

            binding.apply {
                var glideUtil = GlideUtil()
                textViewKategoriAdi.text=kategoriler?.get(position)?.kategoriAd

                glideUtil.UrldekiResmiAl(kategoriler?.get(position)?.kategoriResim.toString(),context,imageViewKategoriImage)


                cardViewKategori.setOnClickListener { onItemClickListener.onItemClick(position) }

            }
        }
    }

    fun setData(yeniKategoriList: List<KategoriResponseItem>?){
        kategoriler=yeniKategoriList
    }


}