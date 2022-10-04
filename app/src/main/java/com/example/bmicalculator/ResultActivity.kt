package com.example.bmicalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.bmicalculator.databinding.ActivityResultBinding
import java.util.*

class ResultActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityResultBinding
    var tts: TextToSpeech? = null
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this,this)
        binding.tips.visibility=View.INVISIBLE
        binding.BMI.visibility=View.INVISIBLE
        var intheight:Float
        binding.calculate.setOnClickListener {
           val BMI = findViewById<TextView>(R.id.BMI)
            val age=intent.getIntExtra("mage",0)
            val weight= intent.getIntExtra("mweight",0)
            val height=intent.getIntExtra("mheight",0)
            intheight=height.toFloat()
            intheight=intheight/100
            Log.e("age_value","$age")
            Log.e("weight _value","$weight")
            Log.e("height _value","$height")
           val bmi = (weight)/ (intheight*intheight)
            binding.BMI.text="Your BMI is $bmi"
            binding.tips.visibility=View.VISIBLE
            binding.BMI.visibility=View.VISIBLE
            showBmi(bmi)
            if (BMI.text.isNotEmpty()){
                speakOut(BMI.text.toString())
            }
        }
    }
    private fun showBmi(bmi: Float) {
        if (bmi<18.5){
            binding.value1.setTextColor(Color.BLUE)
            binding.statusUnderweight.setTextColor(Color.BLUE)
            showResult(R.drawable.underweight,"Take some healthy food")
        }else if (bmi >18.5 && bmi <24.9){
            showResult(R.drawable.normalweight,"You are Healthy")
            binding.value2.setTextColor(Color.GREEN)
            binding.statusNormalweight.setTextColor(Color.GREEN)
        }else if (bmi>25 && bmi <29.9){
            showResult(R.drawable.obese,"Do some exercise")
            binding.value3.setTextColor(Color.RED)
            binding.statusOverweight.setTextColor(Color.RED)
        } else{
            showResult(R.drawable.obese,"Do some Cardio and stop eating junk food")
            binding.value4.setTextColor(Color.RED)
            binding.statusObese.setTextColor(Color.RED)
        }
    }
    private fun showResult(id:Int, tips:String){
        binding.image.setImageResource(id)
        binding.tips.text=tips
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val BMI = tts!!.setLanguage(Locale.US)
            if (BMI ==TextToSpeech.LANG_MISSING_DATA || BMI == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language is not supported")
            }
            else{
                Log.e("TTS","Initilaization Failed")
            }
        }
    }
    public override fun onDestroy() {
        if (tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}

