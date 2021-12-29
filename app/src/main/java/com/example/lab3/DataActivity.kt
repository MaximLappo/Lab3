package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R

class DataActivity : AppCompatActivity() {


    private val db = DataBaseHelper(this)
    private val textID= ArrayList<String>()
    private val textT= ArrayList<String>()
    private val textColor= ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val recyclerView: TextView = findViewById(R.id.textView2)
        recyclerView.movementMethod = ScrollingMovementMethod()
        var text = ""
        storeDataInArrays()

        for (i in textID.indices){
            text += "${textID[i]} , ${textT[i]} , ${textColor[i]}\n"
        }
        recyclerView.text = text
    }

    fun storeDataInArrays(){
        val cursor = db.readAllData()
        if (cursor != null){
            while(cursor.moveToNext()){
                textID.add(cursor.getString(0))
                textT.add(cursor.getString(1))
                textColor.add(cursor.getString(2))
            }
        }
    }
}