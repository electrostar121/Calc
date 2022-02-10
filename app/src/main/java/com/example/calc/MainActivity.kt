package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var currentValue = 0.0
    var savedValue = 0.0
    var dot = true
    var sign = 0
    var startNum = true
    var cleared = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun clearClicker(view: View){

        val outputView = findViewById<TextView>(R.id.tvOutput)

        outputView.text = "0"
        dot = true
        startNum = true
        sign = 0
        currentValue = 0.0
        savedValue = 0.0
        cleared = true

    }

    fun numberPressed(view: View){

        val outputView = findViewById<TextView>(R.id.tvOutput)

        val button = view as Button

        if((outputView.text == "0" && button.text != ".") || startNum){

            outputView.text = button.text
            startNum = false

        }else if(dot || button.text != "."){

            val a = button.text
            val b = outputView.text
            val c = "$b$a"

            outputView.text = c

            if(button.text == "."){

                dot = false

            }

        }

    }

    fun changeSign(view: View){

        val outputView = findViewById<TextView>(R.id.tvOutput)

        if(outputView.text != "0") {

            if(dot) {

                var tmp = outputView.text.toString().toInt()
                tmp *= -1
                outputView.text = tmp.toString()

            }else {

                var tmp = outputView.text.toString().toDouble()
                tmp *= -1
                outputView.text = tmp.toString()

            }

        }

    }

    fun operator(view: View){

        val outputView = findViewById<TextView>(R.id.tvOutput)
        val button = view as Button

        if(button.text == "+"){

            sign = 0

        }else if(button.text == "-"){

            sign = 1

        }else if(button.text == "*"){

            sign = 2

        }else if(button.text == "/"){

            sign = 3

        }

        startNum = true
        cleared = true
        currentValue = outputView.text.toString().toDouble()

    }

    fun equal(view: View){

        val outputView = findViewById<TextView>(R.id.tvOutput)

        val tmp = outputView.text.toString().toDouble()

        if(cleared){

            savedValue = tmp
            cleared = false

        }


        var result = 0.0

        if(sign == 0){

            result = currentValue + savedValue

        }else if(sign == 1){

            result = currentValue - savedValue

        }else if(sign == 2){

            result = currentValue * savedValue

        }else if(sign == 3){

            result = currentValue / savedValue

        }

        outputView.text = result.toString()
        currentValue = result
        startNum = true

    }

}