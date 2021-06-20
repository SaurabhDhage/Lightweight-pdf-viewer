package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Activity(), PdfClickListener {
    private lateinit var rcView: RecyclerView
    private lateinit var pgBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcView = findViewById<RecyclerView>(R.id.rcView)
        pgBar = findViewById<ProgressBar>(R.id.pgBar)
        rcView.isNestedScrollingEnabled = false
        rcView.setHasFixedSize(true)
        rcView.layoutManager = LinearLayoutManager(this)
        val listPdf = FetchPdf(applicationContext)
        val adapter = PdfAdapter(listPdf.getPdf(), this@MainActivity)
        rcView.adapter = adapter
    }

    override fun onRestart() {
        super.onRestart()
        rcView.visibility = View.VISIBLE
        pgBar.visibility = View.GONE
    }

    override fun onPdfClick(pdf: Pdf) {
        val intent = Intent(this, ReaderActivity::class.java)
        intent.putExtra("name", pdf.name)
        intent.putExtra("uri", pdf.uri)
        intent.putExtra("path", pdf.path)


        startActivity(intent)

        rcView.visibility = View.GONE
        pgBar.visibility = View.VISIBLE


    }
}