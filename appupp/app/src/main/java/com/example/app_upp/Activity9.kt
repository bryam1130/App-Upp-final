package com.example.app_upp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_9.*


class Activity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_9)

        ubicacion.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@Activity9,
                    Activity4::class.java
                )
            )
        })

        usuario.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@Activity9,
                    Activity8::class.java
                )
            )
        })

        configuracion.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@Activity9,
                    Activity5::class.java
                )
            )
        })

        ComoUsar.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@Activity9,
                    Activity7::class.java
                )
            )
        })
    }
}