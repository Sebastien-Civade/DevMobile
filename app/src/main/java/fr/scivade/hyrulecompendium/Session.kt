package fr.scivade.hyrulecompendium

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Session {
    private lateinit var toast: Toast

    fun setToast(context : AppCompatActivity){
        this.toast = Toast.makeText(context, "message", Toast.LENGTH_SHORT)
    }

    fun showToast(textId: Int){
        toast.setText(textId)
        toast.show()
    }

    fun showToast(text: String){
        toast.setText(text)
        toast.show()
    }
}