package by.itacademy.android2interface.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.itacademy.android2interface.R
import by.itacademy.android2interface.data.NotebookItem
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add_notebook.*
import kotlinx.android.synthetic.main.activity_main.*

class AddActivity : AppCompatActivity() {
    val TAG = "AddActivity"

    val SELECT_IMAGE_CODE = 2

    var imgUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val model = intent.getStringExtra("EXTRA_MODEL")
        Log.d(TAG, "Model to add notebook: $model")
        etNoteModel.setText(model)

        btnAddNew.setOnClickListener {
            Intent().apply {
                putExtra("EXTRA_model", etNoteModel.text.toString())
                putExtra("EXTRA_screen", etNoteScreen.text.toString())
                putExtra("EXTRA_hardware", etNoteHardware.text.toString())
                putExtra("EXTRA_image", imgUri.toString())

                // Put serializable object:
//                var img: Drawable? = imgUri?.let { uri ->
//                    uriToDrawable(uri)
//                }
//                val newNoteItem = NotebookItem(
//                    img,
//                    etNoteModel.text.toString(),
//                    etNoteScreen.text.toString(),
//                    etNoteHardware.text.toString()
//                )
//                putExtra("EXTRA_new_note", newNoteItem)
                setResult(RESULT_OK, this)
                finish()
            }
        }

        ivNoteImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == SELECT_IMAGE_CODE) {
            imgUri = data?.data
            ivNoteImg.setImageURI(imgUri)
        }
    }

    private fun uriToDrawable(uri: Uri) : Drawable {
        val inputStream = contentResolver.openInputStream(uri)
        val drawable = Drawable.createFromStream(inputStream, uri.toString())
        return drawable
    }
}