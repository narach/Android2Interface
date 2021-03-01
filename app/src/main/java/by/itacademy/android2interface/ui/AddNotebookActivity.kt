package by.itacademy.android2interface.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.itacademy.android2interface.R
import kotlinx.android.synthetic.main.activity_add_notebook.*

class AddNotebookActivity : AppCompatActivity() {
    val TAG = "AddNotebook"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notebook)

        val model = intent.getStringExtra("EXTRA_MODEL")
        Log.d(TAG, "Model to add notebook: $model")

        btnSave.setOnClickListener {
            Intent().apply {
                putExtra("EXTRA_brand", etBrand.text.toString())
                putExtra("EXTRA_model", etModel.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}