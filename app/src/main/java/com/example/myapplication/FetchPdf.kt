package com.example.myapplication

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


class FetchPdf(val applicationContext: Context) {
    val arrayList = ArrayList<Pdf>()
    fun getPdf(): ArrayList<Pdf> {
        val projection = arrayOf(
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATA

        )

        val mimeType = "application/pdf"

        val whereClause = MediaStore.Files.FileColumns.MIME_TYPE + " IN ('" + mimeType + "')"
        val orderBy = MediaStore.Files.FileColumns.SIZE + " DESC"
        val cursor: Cursor? = applicationContext.contentResolver.query(
                MediaStore.Files.getContentUri("external"),
                projection,
                whereClause,
                null,
                orderBy
        )
        val idCol = cursor!!.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        val mimeCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
        val addedCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
        val modifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
        val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
        val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)
        val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
        val DataCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)

        if (cursor.moveToFirst()) {
            do {
                val fileUri: Uri = Uri.withAppendedPath(
                        MediaStore.Files.getContentUri("external"),
                        cursor.getString(idCol)
                )
                val name = cursor.getString(nameCol)
                val data = cursor.getString(DataCol)
                val mimeType = cursor.getString(mimeCol)
                val dateAdded = cursor.getLong(addedCol)
                val dateModified = cursor.getLong(modifiedCol)
                arrayList.add(Pdf(name, fileUri, data))
                // ...
            } while (cursor.moveToNext())
        }
        return arrayList
    }
}