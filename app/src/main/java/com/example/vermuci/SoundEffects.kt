package com.example.vermuci

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.media.AudioAttributes
import android.os.Build


class SoundEffects(context: Context) {

    private var audio: AudioAttributes? = null
    internal val MAX = 2

    init {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audio = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()

            sound = SoundPool.Builder()
                .setAudioAttributes(audio)
                .setMaxStreams(MAX)
                .build()
        } else {
            sound = SoundPool(MAX, AudioManager.STREAM_MUSIC, 0)
        }

        collectSound = sound!!.load(context, com.example.vermuci.R.raw.collect, 1)
        loseSound = sound!!.load(context, com.example.vermuci.R.raw.lose, 1)
    }

    fun collectSound() {
        sound!!.play(collectSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun loseSound() {
        sound!!.play(loseSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    companion object {

        private var collectSound: Int = 0
        private var loseSound: Int = 0
        private var sound: SoundPool? = null
    }
}