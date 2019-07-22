﻿package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender
import android.view.inputmethod.EditorInfo


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView
    lateinit var benderObj : Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

        //обработка отправки сообщения нажатием на энтер
        messageEt.imeOptions = EditorInfo.IME_ACTION_DONE
        messageEt.setOnEditorActionListener{ v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                hideKeyboard()
                sendMessage()
                true
            } else false
        }

        //обработка отправки сообщения нажатием на иконку отправки
        sendBtn.setOnClickListener(this)

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)

        super.onSaveInstanceState(outState)
    }


    override fun onClick(v: View?) {
        hideKeyboard()
        if (v?.id == R.id.iv_send){
            sendMessage()
        }

    }

    fun sendMessage(){
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        messageEt.setText("")
        val (r,g,b) = color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
    }
}