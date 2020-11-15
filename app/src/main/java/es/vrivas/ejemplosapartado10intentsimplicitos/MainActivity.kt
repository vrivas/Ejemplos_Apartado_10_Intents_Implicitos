package es.vrivas.ejemplosapartado10intentsimplicitos

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im_browser.setOnClickListener(this)
        im_mail.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when( v ) {
            im_browser -> {
                try {
                    val navegador =
                        Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://duckduckgo.com/?q=Victor+Rivas+Santos"))
                    startActivity(navegador)
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "No se pudo lanzar un navegador web: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } // im_browser
            im_mail -> {
                try {
                    val datosMail: String = "mailto:vrivas@ujaen.es" +
                            "?cc=victorrivassantos@gmail.com" +
                            "&subject=" +
                            Uri.encode("Estoy probando a enviar correos desde Android") +
                            "&body=" +
                            Uri.encode("Hola Víctor:\n Estoy enviando un correo. \n Saludos.");

                    val email = Intent(Intent.ACTION_SENDTO, Uri.parse(datosMail));
                    startActivity(email)
                } catch( e: Exception ) {
                    Toast.makeText(this
                            , "No se puede enviar el email: ${e.localizedMessage}"
                            , Toast.LENGTH_SHORT).show();
                }
            } // im_mail
        }
    }
}