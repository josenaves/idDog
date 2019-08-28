package com.josenaves.iddog.presentation.dog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josenaves.iddog.R
import com.josenaves.iddog.model.Dog

class DogAdapter(private val dogList: List<Dog>,
                 private val clickListener: ((Dog) -> Unit)? = null
): RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    override fun getItemCount() = dogList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.image_layout, parent, false)

        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogList[position]
        holder.bindDog(dog)

    }

    class DogViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var dog : Dog? = null

        private val image: ImageView = view.findViewById(R.id.dogImage)

        fun bindDog(dog: Dog) {
            this.dog = dog
            Glide.with(view)
                .load(dog.imageUrl)
                .centerCrop()
                .into(image)
        }

        //        holder.dogView.image = dog.title

//        Glide.with(itemView)
//            .load(dog.imageUrl)
//            .centerCrop()
////            .placeholder(R.drawable.ic_image_place_holder) //5
////            .error(R.drawable.ic_broken_image) //6
////            .fallback(R.drawable.ic_no_image) //7
//            .into(itemView.ivPhoto) //8

//        holder.dogImageView.setOnClickListener {
//            clickListener.invoke(dog)
//        }

    }

}