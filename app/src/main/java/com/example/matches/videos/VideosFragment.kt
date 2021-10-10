package com.example.matches.videos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matches.BR
import com.example.matches.R
import com.example.matches.databinding.FragmentVideosBinding
import com.example.matches.helper.StaticVideosList
import com.example.matches.model.VideoDetails
import com.example.matches.videos.adapter.VideoAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideosFragment : Fragment(), VideoAdapter.OnServiceCallBack{

    lateinit var mViewModel: VideosViewModel
    lateinit var mDataBinding: FragmentVideosBinding
    var adapter =VideoAdapter()
    companion object{
        private var invoicesList=StaticVideosList().getInvoiceList()
    }

    private var youTubePlayerView: YouTubePlayerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_videos,
            container,
            false
        )
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewModel execution
        executeViewModel()

        // Adapting recyclerView
        executeRecyclerView()


    }

    private fun executeViewModel() {
        mViewModel = ViewModelProvider(this).get(VideosViewModel::class.java)
        mDataBinding.setVariable(BR.videosViewModel, mViewModel)
        mDataBinding.lifecycleOwner = this
        mDataBinding.executePendingBindings()
    }

    private fun executeRecyclerView() {
        var recyclerView = mDataBinding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(linearLayoutManager)
        recyclerView.setHasFixedSize(true)
        adapter.setOnServiceCallBack(this)
        recyclerView.setAdapter(adapter)
        adapter.refreshItems(invoicesList)
    }

    override fun updateUrl(youtubePlayerView: YouTubePlayerView, videoDetails: VideoDetails) {

        this.youTubePlayerView = youtubePlayerView
        getLifecycle().addObserver(youTubePlayerView!!)
        Log.d("TAG", "updateUrl: ${PlayerConstants.PlayerState.BUFFERING}")

        this.youTubePlayerView!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = videoDetails.videoId

                youTubePlayer.cueVideo(videoId, 0f)

            }
        })
    }


}