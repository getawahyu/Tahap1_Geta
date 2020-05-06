package com.example.biodatadiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var namaI : String = ""
    private var emailI : String = ""
    private var telponI : String = ""
    private var alamatI : String = ""
    private var genderI : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataSpinnerGender()

        btnSimpan.setOnClickListener { validasiInput() }
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.jenis_kelamin, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        jenisKelamin.adapter = adapter
    }

    private fun validasiInput(){
        namaI = edtNama.text.toString()
        emailI = edtEmail.text.toString()
        telponI = edtTelpon.text.toString()
        alamatI = edtAlamat.text.toString()
        genderI = jenisKelamin.selectedItem.toString()

        when{
            namaI.isEmpty() -> edtNama.error = "Nama wajib di isi"
            genderI.equals("Jenis Kelamin", ignoreCase = true) ->
                tampilToast("Jenis Kelamin harus dipilih")
            emailI.isEmpty() -> edtEmail.error = "Email wajib di isi"
            telponI.isEmpty() -> edtTelpon.error = "Telpon wajib di isi"
            alamatI.isEmpty() -> edtAlamat.error = "Alamat wajib di isi"

            else -> {
                tampilToast("Navigasi ke halaman profil")
                goToProfileActivity()
            }
        }
    }

    private fun tampilToast(massage: String) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
    }

    //intent + passing data
    private fun goToProfileActivity(){
        val intent = Intent(this, Profile_activity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaI)
        bundle.putString("gender", genderI)
        bundle.putString("email", emailI)
        bundle.putString("telpon", telponI)
        bundle.putString("alamat", alamatI)

        intent.putExtras(bundle)

        startActivity(intent)
    }

}



