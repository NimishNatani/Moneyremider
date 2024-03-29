package com.practicecoding.moneyreminder.UserInterface

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practicecoding.moneyreminder.R
import com.practicecoding.moneyreminder.Room.Note
import com.practicecoding.moneyreminder.databinding.CardItemBinding

class Myadapter(val context: Context,
                val noteClickDeleteInterface: NoteClickDeleteInterface,
                val noteClickInterface: NoteClickInterface):RecyclerView.Adapter<Myadapter.Myviewholder>() {
    private val allNotes = ArrayList<Note>()

    inner class Myviewholder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root)
    {
fun bind(note: Note)
{
    binding.username.text=note.name
    val str = note.timestamp
    binding.usertime.text = "Last Updated : $str "
    binding.useramount.text = note.amount
    binding.delete.setOnClickListener(){
        noteClickDeleteInterface.onDeleteIconClick(note)}

}}
        fun updatelist(newlist:List<Note>){
            allNotes.clear()
            allNotes.addAll(newlist)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.card_item,parent,false
        )
        return Myviewholder(binding)
    }

    override fun getItemCount(): Int {
return allNotes.size   }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
holder.bind(allNotes[position] )    }
}
interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}