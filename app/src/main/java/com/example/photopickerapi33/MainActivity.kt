package com.example.photopickerapi33

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val wrapper = NewPhotoPickerWrapper(this,2)

    private val button: Button by lazy{
        findViewById(R.id.button)
    }

    private val imageView1: ImageView by lazy {
        findViewById(R.id.imageView1)
    }

    private val imageView2: ImageView by lazy {
        findViewById(R.id.imageView2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
        PhotoPickerSelectorFragment(
            onSingle = {sheet ->

                sheet.dismiss()

                wrapper.singleImage(
                    onSingle = {uri ->
                        imageView1.setImageURI(null)
                        imageView2.setImageURI(null)

                        imageView1.setImageURI(uri)

                    },
                    onEmpty = {
                        Toast.makeText(this, "Select nothing", Toast.LENGTH_SHORT).show()
                    }
                )
            },

            onMultiple = {sheet ->
                sheet.dismiss()

                wrapper.multipleImage(
                    onMultiple = {uris ->
                        imageView1.setImageURI(null)
                        imageView2.setImageURI(null)

                        if(uris.size == 1){
                            imageView1.setImageURI(uris[0])
                        }
                        if(uris.size == 2){
                            imageView1.setImageURI(uris[0])
                            imageView2.setImageURI(uris[1])
                        }


                    },
                    onEmpty = {
                        Toast.makeText(this, "Select nothing", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        ).show(supportFragmentManager, "PhotoPickerSelectorFragment")

        }
    }
}