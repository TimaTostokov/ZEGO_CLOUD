package com.example.zego_cloud

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment

class LiveAudioRoomActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_live_audio_room)

        val roomIdTextView = findViewById<TextView>(R.id.room_id_tx)
        roomIdTextView.text = "Room ID : " + intent.getStringExtra("roomId")

        addFragment()
    }

    private fun addFragment() {
        val appID: Long = KeyConstants.appId
        val appSign = KeyConstants.appSign
        val userID = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("username")

        val isHost = intent.getBooleanExtra("isHost", false)
        val roomID = intent.getStringExtra("roomId")

        val config: ZegoUIKitPrebuiltLiveAudioRoomConfig = if (isHost) {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.host()
        } else {
            ZegoUIKitPrebuiltLiveAudioRoomConfig.audience()
        }
        val fragment = ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(
            appID, appSign, userID, userName, roomID, config
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }

}