package com.example.matches.videos.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matches.R
import com.example.matches.model.VideoDetails
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    private var videoList = ArrayList<VideoDetails>()
    private var onServiceCallBack: OnServiceCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.video_card_view, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.onBind(videoList[position])

    }

    override fun getItemCount(): Int {
        return videoList.size
    }


    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var youtubePlayerView = view.findViewById<YouTubePlayerView>(R.id.activity_main_youtubePlayerView)

        fun onBind(videoDetails: VideoDetails) {

            onServiceCallBack?.updateUrl(youtubePlayerView, videoDetails)

        }
    }

    fun refreshItems(invoice: List<VideoDetails>) {
        videoList.clear()
        videoList.addAll(invoice)
        notifyDataSetChanged()
    }

    fun setOnServiceCallBack(listener: OnServiceCallBack) {
        onServiceCallBack = listener
    }

    interface OnServiceCallBack {
        fun updateUrl(youtubePlayerView: YouTubePlayerView, videoDetails: VideoDetails)
    }

}