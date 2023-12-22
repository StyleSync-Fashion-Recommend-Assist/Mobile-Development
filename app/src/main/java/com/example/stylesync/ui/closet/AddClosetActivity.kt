package com.example.stylesync.ui.closet

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.stylesync.TensorFlowHelper
import com.example.stylesync.databinding.ActivityAddClosetBinding
import com.example.stylesync.list.ImportantLists
import java.io.FileOutputStream
import java.io.IOException

class AddClosetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddClosetBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var currentPhotoPath: String
    private lateinit var tfLiteHelper: TensorFlowHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClosetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateSpinnerCategory()
        populateSpinnerSubCategory()
        populateSpinnerColor()
        checkIfCalledAsDetailScreen()

        binding.imageView.setOnClickListener{
            dispatchTakePictureIntent()
        }
    }

    private fun populateSpinnerCategory() {
        val categoryNamesList: List<String> = ImportantLists.categoriesList.map { it.category }
        val spinnerCategory: Spinner = binding.spinnerCategory
        val adapterCategory =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNamesList)
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapterCategory

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val selectedCategory: String =
                    parentView.getItemAtPosition(position) as String
                val categoryId = selectedCategory
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun populateSpinnerSubCategory() {
        val subCategoryNamesList: List<String> = ImportantLists.subCategoryList.map { it.SubCategory }
        val spinnerSubCategory: Spinner = binding.spinnerSubCategory
        val adapterSubCategory =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, subCategoryNamesList)
        adapterSubCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubCategory.adapter = adapterSubCategory

        spinnerSubCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val selectedSubCategory: String =
                    parentView.getItemAtPosition(position) as String
                val subCategory = selectedSubCategory
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun populateSpinnerColor() {
        val colorNamesList: List<String> = ImportantLists.colorList.map {it.color}
        val spinnerColor: Spinner = binding.spinnerColor
        val adapterColor =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, colorNamesList)
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColor.adapter = adapterColor

        spinnerColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                val selectedColor: String =
                    parentView.getItemAtPosition(position) as String
                val color = selectedColor
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun checkIfCalledAsDetailScreen() {

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                @Suppress("DEPRECATION")
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            @Suppress("DEPRECATION")
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageView.setImageBitmap(imageBitmap)
//            tfLiteHelper = TensorFlowHelper(this)
//            val outputResult = tfLiteHelper.processImage(imageBitmap)
//            binding.textViewOccupation.text = outputResult.toString()
        }
    }

    private fun saveItem(bitmap: Bitmap) {
        saveBitmapToInternalStorage(bitmap)
    }

    private fun saveBitmapToInternalStorage(bitmap: Bitmap) {
        val filename = "filename.jpg"

        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream)
            fileOutputStream.close()

            // Get the path where the image is saved
            currentPhotoPath = getFileStreamPath(filename).absolutePath
            Log.d("ImagePath", "Image saved at: $currentPhotoPath")

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}