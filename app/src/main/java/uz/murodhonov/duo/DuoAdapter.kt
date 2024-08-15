package uz.murodhonov.duo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.murodhonov.duo.databinding.MenuRowItemBinding

class MenuAdapter(private val titles: List<String>, private val images:List<Int>) : BaseAdapter() {

    private val optionViews = mutableListOf<View>()

    override fun getCount(): Int {
        return titles.size
    }

    override fun getItem(position: Int): Any {
        return titles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: MenuRowItemBinding
        val view: View

        if (convertView == null) {
            binding = MenuRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            view = binding.root
            view.tag = binding

            // Add each view to the list of option views
            optionViews.add(view)
        } else {
            binding = convertView.tag as MenuRowItemBinding
            view = convertView
        }

        binding.menuText.text = titles[position]
        binding.imageIcon.setImageDrawable(view.context.resources.getDrawable(images[position], null))
        return view
    }

    fun setViewSelected(position: Int, selected: Boolean) {
        // Looping through the options in the menu and selecting the chosen option
        for (i in optionViews.indices) {
            if (i == position) {
                optionViews[i].isSelected = selected
            } else {
                optionViews[i].isSelected = !selected
            }
        }
    }
}
