package com.example.mvvm.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.Model.Blog
import com.example.mvvm.ViewModel.MainViewModel
import android.view.animation.Animation
import com.example.mvvm.R


class NoteRecyclerAdapter (val viewModel: MainViewModel, val arrayList: ArrayList<Blog>, val context: Context): RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {
    var  lastPosition : Int = -1;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoteRecyclerAdapter.NotesViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(com.example.mvvm.R.layout.item_view,parent,false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: NoteRecyclerAdapter.NotesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
        setAnimation(holder.itemView, position);
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty",Toast.LENGTH_LONG).show()
        }else{

        }
        return arrayList.size
    }


    inner  class NotesViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(blog: Blog){
         //   binding.title.text = blog.title
            var title : TextView=itemView.findViewById(R.id.title)
            title.setText(blog.title)
           var button : ImageButton=itemView.findViewById(R.id.delete)
            button.setOnClickListener {
                viewModel.remove(blog)
                notifyDataSetChanged()
            }


         /*   binding.delete.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }

          */
        }

    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation = android.view.animation.AnimationUtils.loadAnimation(context,com.example.mvvm.R.anim.item_animation_fall_down)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}