package com.menu.orders.adapter

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cdr.orders.model.Order
import com.menu.orders.R
import com.menu.orders.databinding.ItemPersonBinding
import com.menu.orders.model.Person
import ru.here.menu.R
import ru.here.menu.databinding.ItemOrderBinding
import ru.here.menu.databinding.ItemPersonBinding

// DiffUtil, который не вошел в статью:
class PersonDiffUtil(
    private val oldList: List<Person>,
    private val newList: List<Person>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPerson = oldList[oldItemPosition]
        val newPerson = newList[newItemPosition]
        return oldPerson.id == newPerson.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPerson = oldList[oldItemPosition]
        val newPerson = newList[newItemPosition]
        return oldPerson == newPerson
    }
}

interface PersonActionListener {
    fun onPersonGetId(person: Person)
    fun onPersonLike(person: Person)
    fun onPersonRemove(person: Person)
    fun onPersonMove(person: Person, moveBy: Int)
}

class PersonAdapter(
    private val orders: List<Order>,
    private val onItemClick: (Order) -> Unit
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
}

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = data[position] // Получение человека из списка данных по позиции
        val context = holder.itemView.context

        with(holder.binding) {
            holder.itemView.tag = person
            likedImageView.tag = person
            more.tag = person

            val color =
                if (person.isLiked) R.color.red else R.color.grey // Цвет "сердца", если пользователь был лайкнут

            nameTextView.text = person.name // Отрисовка имени пользователя
            companyTextView.text = person.companyName // Отрисовка компании пользователя
            likedImageView.setColorFilter( // Отрисовка цвета "сердца"
                ContextCompat.getColor(context, color),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            Glide.with(context).load(person.photo)
                .circleCrop() // Отрисовка фотографии пользователя с помощью библиотеки Glide
                .error(R.drawable.ic_person)
             //   .placeholder(R.drawable.ic_person).into(imageView)
        }
    }

    override fun onClick(view: View) {
        val person: Person = view.tag as Person // Получаем из тэга человека

        when (view.id) {
            R.id.more -> showPopupMenu(view)
            R.id.likedImageView -> personActionListener.onPersonLike(person)
            else -> personActionListener.onPersonGetId(person)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val person = view.tag as Person
        val position = data.indexOfFirst { it.id == person.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, "Up").apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, "Down").apply {
            isEnabled = position < data.size - 1
        }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> personActionListener.onPersonMove(person, -1)
                ID_MOVE_DOWN -> personActionListener.onPersonMove(person, 1)
                ID_REMOVE -> personActionListener.onPersonRemove(person)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }


