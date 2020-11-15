package es.vrivas.ejemplosapartado10intentsimplicitos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im_browser.setOnClickListener(this)
        im_mail.setOnClickListener(this)
        im_txt.setOnClickListener(this)
        im_map.setOnClickListener(this)
        im_contact.setOnClickListener(this)
        im_youtube.setOnClickListener(this)
    }

    // Envía un intent a un navegador disponible en el dispositivo
    fun intent_browser() {
        try {
            val uri = Uri.parse("https://duckduckgo.com/?q=Victor+Rivas+Santos")
            val navegador =Intent(Intent.ACTION_VIEW, uri)
            startActivity(navegador)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "No se pudo lanzar un navegador web: ${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Envía un correo electrónico
    fun intent_mail() {
        try {
            val datosMail: String = "mailto:vrivas@ujaen.es" +
                    "?cc=victorrivassantos@gmail.com" +
                    "&subject=" +
                    Uri.encode("Estoy probando a enviar correos desde Android") +
                    "&body=" +
                    Uri.encode("Hola Víctor:\n Estoy enviando un correo. \n Saludos.")

            val email = Intent(Intent.ACTION_SENDTO, Uri.parse(datosMail))
            startActivity(email)
        } catch (e: Exception) {
            Toast.makeText(
                this, "No se puede enviar el email: ${e.localizedMessage}", Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Envía un mensaje de texto
    fun intent_txt() {
        try {
            val mensaje="Hola. ¿Cómo estás?"

            val texto = Intent(Intent.ACTION_SEND)
            texto.setType("text/plain")
            texto.putExtra(Intent.EXTRA_TEXT, mensaje)
            startActivity(texto)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "No se puede enviar el mensaje de texto: ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Envía una geolocalización
    fun intent_map() {
        try {
            val localidad="Jaen"
            val uri=Uri.parse("geo:0,0?q=$localidad")
            val mapa = Intent(Intent.ACTION_VIEW, uri)
            startActivity(mapa)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "No se puede mostrar el mapa: ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Llama a la aplicación YouTube
    fun intent_youtube() {
        try {
            val app =
                packageManager.getLaunchIntentForPackage("com.google.android.youtube")
            startActivity(app)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "No se puede invocar a YouTube: ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Obtiene los datos de contacto
    fun intent_contact() {
        try {
            val REQUEST_SELECT_CONTACT = 1

            val contact = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }
            startActivityForResult(contact, REQUEST_SELECT_CONTACT)
        } catch (e: Exception){
            Toast.makeText(
                this,
                "No se puede acceder a los contactos: ${e.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClick(v: View?) {
        when( v ) {
            im_browser -> intent_browser()
            im_mail -> intent_mail()
            im_txt -> intent_txt()
            im_map -> intent_map()
            im_youtube -> intent_youtube()
            im_contact -> intent_contact()
        }
    }
}