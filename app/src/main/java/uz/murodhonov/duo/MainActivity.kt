package uz.murodhonov.duo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import uz.murodhonov.duo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mTitles = arrayListOf(
            "Bosh sahifa",
            "Mening Ogohlantirishlarim",
            "Shikoyatlar",
            "Qo'llab quvvatlash",
            "Parolni o'zgartirish"
        )
        val mImages = arrayListOf(
            R.drawable.home_icon,
            R.drawable.bell,
            R.drawable.message_circle,
            R.drawable.user_check,
            R.drawable.lock
        )
        val duoMenuView = binding.duoMenuView
        val menuAdapter = MenuAdapter(mTitles, mImages)
        duoMenuView.adapter = menuAdapter

        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.hamburger, theme)
        window.statusBarColor = ContextCompat.getColor(this, R.color.green_background)


        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        duoMenuView.setOnMenuClickListener(object : DuoMenuView.OnMenuClickListener {
            override fun onFooterClicked() {

            }

            override fun onHeaderClicked() {

            }

            override fun onOptionClicked(position: Int, objectClicked: Any?) {
                title = mTitles[position]
                binding.tvMain.text = title
                menuAdapter.setViewSelected(position, true)
                binding.drawerLayout.closeDrawer()

                /*when (position) {
                    0 -> {
                        Toast.makeText(this@MainActivity, "first", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
                        Toast.makeText(this@MainActivity, "second", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(this@MainActivity, "other", Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        })

    }

    // Optionally handle the back button press to close the drawer if it's open
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }
}
