package by.itacademy.android2interface.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.android2interface.R
import by.itacademy.android2interface.data.NotebookItem
import kotlinx.android.synthetic.main.item_note.view.*

class NotebooksAdaptor(
    var notebooks: List<NotebookItem>
) : RecyclerView.Adapter<NotebooksAdaptor.NotebooksViewHolder>() {

    inner class NotebooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotebooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebooksViewHolder, position: Int) {
        holder.itemView.apply {
            tvModel.text = notebooks[position].model
            tvScreen.text = notebooks[position].screen
            tvCharacterisctics.text = notebooks[position].hardware
            ivNote.setImageDrawable(notebooks[position].img)
        }
    }

    override fun getItemCount(): Int {
        return notebooks.size
    }
}