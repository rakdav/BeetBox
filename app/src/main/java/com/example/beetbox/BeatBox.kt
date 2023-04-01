package com.example.beetbox

import android.content.res.AssetManager
import android.util.Log

private const val TAG="BeatBox"
private const val SOUNDS_FOLDER="sample_sounds"
class BeatBox(private val assets:AssetManager) {
    fun loadSounds():List<String>{
        try
        {
            val soundNames=assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG,"Found ${soundNames.size} sounds")
            return soundNames.asList()
        }
        catch (e:Exception)
        {
            Log.e(TAG,"Could not list assets",e)
            return emptyList()
        }
    }
}