package com.example.myapplication


import android.app.Activity
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.viewpager.widget.ViewPager
import java.io.File


class ReaderActivity : Activity() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var renderer: PdfRenderer


    override fun onCreate(savedInstanceState: Bundle?) {
        val name = intent.getStringExtra("name")
        // actionBar?.title = name
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        viewPager = findViewById(R.id.viewPagerMain)
        val uri = intent.getParcelableExtra<Uri>("uri")
        val path = intent.getStringExtra("path")
        viewPager.offscreenPageLimit = 5
        val input = ParcelFileDescriptor.open(File(path), ParcelFileDescriptor.MODE_READ_ONLY)
        renderer = PdfRenderer(input)
        viewPagerAdapter = ViewPagerAdapter(this, renderer)
        viewPager.adapter = viewPagerAdapter


    }


}