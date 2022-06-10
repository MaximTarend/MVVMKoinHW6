package by.hometrainng.mvvmkoinhw6.adapters

import androidx.recyclerview.widget.RecyclerView
import by.hometrainng.mvvmkoin6.domain.model.Beer
import by.hometrainng.mvvmkoinhw6.databinding.ListItemBinding
import coil.load

class ItemViewHolder(
    private val binding: ListItemBinding,
    private val onClicked: (Beer) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind (item: Beer) {
        with(binding) {
            image.load(item.imageURL)
            name.text = item.name

            root.setOnClickListener {
                onClicked(item)
            }
        }
    }
}