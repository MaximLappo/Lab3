package com.example.lab3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedEntry : BaseColumns {
    const val TABLE_NAME = "text_table"
    const val COLUMN_NAME_FIRST = "text"
    const val COLUMN_NAME_SECOND = "color"

}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${FeedEntry.COLUMN_NAME_FIRST} TEXT," +
            "${FeedEntry.COLUMN_NAME_SECOND} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }


    fun addRow(text: String, color: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(FeedEntry.COLUMN_NAME_FIRST, text)
            put(FeedEntry.COLUMN_NAME_SECOND, color)
        }
        db?.insert(FeedEntry.TABLE_NAME, null, values)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "txt.db"
    }


    fun readAllData(): Cursor?{
        val quary = "SELECT * FROM " + FeedEntry.TABLE_NAME
        val db = this.readableDatabase

        var cursor: Cursor? = null
        if (db != null){
            cursor = db.rawQuery(quary, null)
        }
        return cursor
    }


}
