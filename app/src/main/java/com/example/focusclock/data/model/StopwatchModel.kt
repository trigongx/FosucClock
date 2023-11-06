package com.example.focusclock.data.model

class StopwatchModel {
    private var isRunning = false
    private var elapsedTime = 0L
    private var startTime = 0L

    fun start(){
        if (!isRunning){
            startTime = System.currentTimeMillis() - elapsedTime
            isRunning = true
        }
    }

    fun stop(){
        if (isRunning){
            elapsedTime = System.currentTimeMillis() - startTime
            isRunning = false
        }
    }
    fun reset(){
        isRunning = false
        elapsedTime = 0L
    }
    fun getTime():Long{
        return if (isRunning){
            System.currentTimeMillis() - startTime
        } else {
            elapsedTime
        }
    }
}