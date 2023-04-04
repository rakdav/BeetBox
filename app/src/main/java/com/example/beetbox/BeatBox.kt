package com.example.beetbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG="BeatBox"
private const val SOUNDS_FOLDER="sample_sounds"
private const val MAX_SOUNDS=5
class BeatBox(private val assets:AssetManager) {
    val sounds:List<Sound>
    private val soundPoll=SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()
    init {
        sounds=loadSounds()
    }
    fun play(sound: Sound)
    {
        sound.soundId?.let {
            soundPoll.play(it,1.0f,1.0f,1,0,1.0f)
        }
    }
    fun loadSounds():List<Sound>{
        val soundNames:Array<String>
        try
        {
            soundNames=assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG,"Found ${soundNames.size} sounds")
           // return soundNames.asList()
        }
        catch (e:Exception)
        {
            Log.e(TAG,"Could not list assets",e)
            return emptyList()
        }
        val sounds= mutableListOf<Sound>()
        soundNames.forEach { fileName->
            val  assetPath="$SOUNDS_FOLDER/$fileName"
            val sound=Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            }
            catch (ioe:IOException)
            {
                Log.e(TAG,"Can not load sound $fileName",ioe)
            }
        }
        return sounds
    }
    private fun load(sound:Sound){
        val afd:AssetFileDescriptor=assets.openFd(sound.assetPath)
        val soundId=soundPoll.load(afd,1)
        sound.soundId=soundId
    }
}