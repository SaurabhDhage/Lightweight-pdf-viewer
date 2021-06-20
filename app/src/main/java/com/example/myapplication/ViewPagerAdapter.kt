package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.github.chrisbanes.photoview.PhotoView
import java.util.*

class ViewPagerAdapter(// Context object
        private var context: Context, // Array of images
        private var renderer: PdfRenderer
) : PagerAdapter() {
    // Layout Inflater
    var mLayoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        // return the number of images
        return renderer.pageCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating the item.xml
        val itemView = mLayoutInflater.inflate(R.layout.slide_item, container, false)

        // referencing the image view from the item.xml file
        val imageView = itemView.findViewById<PhotoView>(R.id.imageViewMain)

        // setting the image in the imageView
        val page = renderer.openPage(position)
        val bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888)

        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        imageView.setImageBitmap(bitmap)
        // Glide.with(context).load(bitmap).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)

        // Adding the View
        page.close()
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}