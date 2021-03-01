package by.itacademy.android2interface.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.android2interface.R
import by.itacademy.android2interface.adaptors.NotebooksAdaptor
import by.itacademy.android2interface.data.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val ADD_NOTEBOOK_CODE = 1
    val TAG = "MainActivity"

    var notebooksList = mutableListOf<Notebook>()
    var itemsList = mutableListOf<NotebookItem>()
    lateinit var adapter: NotebooksAdaptor

    init {
        val screenAsus = Screen(15.6f, 1920, 1080)
        val procAsus = Processor("Intel", "Core I5 10300H", 2500, 4)
        val videoAsus = VideoCard("NVIDIA", "GeForce GTX 1650 Ti", 4)
        val noteAsus = Notebook("Asus", "TUF Gaming F15", screenAsus,
            procAsus,8, 512, videoAsus)
        notebooksList.add(noteAsus)

        val noteHonor = Notebook(
            "Honor",
            "Magic 14 2020 53010 VTY",
            Screen(14.0f, 1920, 1080),
            Processor("AMD", "Ryzen 5 3500U", 2100, 4),
            8,
            512,
            null
        )
        notebooksList.add(noteHonor)

        notebooksList.add(
            Notebook(
            "Apple",
            "Macbook Air 13 M1 2020",
            Screen(13.3f, 2560, 1600),
            Processor("Apple", "M1", 3200, 8),
            8,
            256,
            VideoCard("Apple", "M1 GPU", null)
        )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsList = mutableListOf(
            NotebookItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.asus_tuf),
                "Asus TUF Gaming F15",
                "15.6, 1920 x 1080",
                "Intel Core I5, 16Gb RAM, 512Gb SSD, 4Gb Video"
            ),
            NotebookItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.honor_magic_book),
                "Honor Magic 14 2020 53010 VTY",
                "14.0, 1920 x 1080",
                "AMD Ryzen 5, 8Gb RAM, 512Gb SSD"
            ),
            NotebookItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.apple_mac_book_air),
                "Apple Macbook Air 13 M1 2020",
                "13.3, 2560 x 1600",
                "Apple M1, 8Gb RAM, 256Gb SSD, M1 GPU Video"
            )
        )

        adapter = NotebooksAdaptor(itemsList)
        rvNotes.adapter = adapter
        rvNotes.layoutManager = LinearLayoutManager(this)

        btnAddNote.setOnClickListener {
            Intent(this, AddActivity::class.java).also {
                it.putExtra("EXTRA_MODEL", "HP")
                startActivityForResult(it, ADD_NOTEBOOK_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTEBOOK_CODE) {
            if(resultCode == RESULT_OK) {
                val model = data?.getStringExtra("EXTRA_model") ?: ""
                val screen = data?.getStringExtra("EXTRA_screen")
                val hardware = data?.getStringExtra("EXTRA_hardware")
                val imgUri = data?.getStringExtra("EXTRA_image")
                val inputStream = contentResolver.openInputStream(Uri.parse(imgUri))
                val drawable = Drawable.createFromStream(inputStream, imgUri)
                Log.d(TAG, "New note: $imgUri, $model, $screen, $hardware")

                val newNotebook = NotebookItem(drawable, model, screen, hardware)
//                val newNotebook = data?.getSerializableExtra("EXTRA_new_note") as NotebookItem

                itemsList.add(newNotebook)
                adapter.notifyItemInserted(itemsList.size - 1)
            }
        }
    }
}