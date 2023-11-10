package fr.scivade.hyrulecompendium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val galleryFragment= GalleryFragment(this)

    val retrofit = RetrofitServiceBuilder.getRetrofitService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(this)

        loadGalleryFragment()
    }

    private fun setUpToolbar(mainActivityContext: MainActivity){
        val toolBar = findViewById<Toolbar>(R.id.main_activity_toolbar)
        toolBar.apply {
            setTitle(R.string.toolbar_title)
            setTitleTextAppearance(mainActivityContext, R.style.ToolbarTheme)
        }
        setSupportActionBar(toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadGalleryFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_fragment_container, galleryFragment)
            .commit()
    }
}