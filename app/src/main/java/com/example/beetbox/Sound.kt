package com.example.beetbox
private const val WAV=".wav"

class Sound(val assetPath:String,var soundId:Int?=null) {
    var name=assetPath.split("/").last().removePrefix(WAV)
}