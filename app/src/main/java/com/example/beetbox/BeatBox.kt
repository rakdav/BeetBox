package com.example.beetbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG="BeatBox"
private const val SOUNDS_FOLDER="sample_sounds"
class BeatBox(private val assets:AssetManager) {
    val sounds:List<Sound>
    init {
        sounds=loadSounds()
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
            sounds.add(sound)
        }
        return sounds
    }
}