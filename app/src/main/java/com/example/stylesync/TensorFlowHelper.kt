package com.example.stylesync

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TensorFlowHelper(context: Context) {

    private val interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModelFile(context))
    }

    private fun loadModelFile(context: Context): ByteBuffer {
        val modelFile = context.assets.openFd("converted_model.tflite")
        val inputStream = FileInputStream(modelFile.fileDescriptor)

        val modelBuffer = ByteBuffer.allocateDirect(modelFile.declaredLength.toInt())
        inputStream.channel.read(modelBuffer)

        return modelBuffer
    }

    fun processImage(bitmap: Bitmap): FloatArray {
        val inputByteBuffer = convertBitmapToByteBuffer(bitmap)

        val output = Array(1) { FloatArray(NUM_CLASSES) }
        interpreter.run(inputByteBuffer, output)

        return output[0]
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val inputSize = 224
        val numChannels = 3

        val byteBuffer = ByteBuffer.allocateDirect(4 * inputSize * inputSize * numChannels)
        byteBuffer.order(ByteOrder.nativeOrder())
        return byteBuffer
    }

    private fun getNormalizedValues(pixelValue: Int): FloatArray {
        val red = (Color.red(pixelValue) / 255.0).toFloat()
        val green = (Color.green(pixelValue) / 255.0).toFloat()
        val blue = (Color.blue(pixelValue) / 255.0).toFloat()

        return floatArrayOf(red, green, blue)
    }

    companion object {
        private const val NUM_CLASSES = 3
    }
}