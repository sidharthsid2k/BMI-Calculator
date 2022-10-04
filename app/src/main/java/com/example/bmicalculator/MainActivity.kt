package com.example.bmicalculator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.bmicalculator.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedRadioButton: RadioButton
    private lateinit var tvage:TextView
    private lateinit var tvprogress:TextView
    private lateinit var tvweight:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       tvage=findViewById(R.id.tvage)
        tvweight=findViewById(R.id.tvweight)
        tvprogress=findViewById(R.id.tvprogress)
        var increament_number=0
        var decreament_number=0
       binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
           override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               tvprogress.text=progress.toString()
           }
           override fun onStartTrackingTouch(seekBar: SeekBar?) {
           }
           override fun onStopTrackingTouch(seekBar: SeekBar?) {
           }
       })
        binding.submit.setOnClickListener {
            selectedRadioButton = binding.radioGroup1.findViewById(binding.radioGroup1.checkedRadioButtonId)
            if (selectedRadioButton!=null){
                Toast.makeText(this,selectedRadioButton.text,Toast.LENGTH_SHORT).show()
            }
        }
        binding.increasedage.setOnClickListener {
            increament_number++
            tvage.text=increament_number.toString()
        }
        binding.decreaseage.setOnClickListener{
            decreament_number=increament_number--
            tvage.text=decreament_number.toString()
        }
        binding.increaseweight.setOnClickListener {
            increament_number++
            tvweight.text=increament_number.toString()
        }
        binding.decreaseweight.setOnClickListener {
            decreament_number=increament_number--
            tvweight.text=decreament_number.toString()
        }
        binding.Find.setOnClickListener {
val intent = Intent(this,ResultActivity::class.java)
            val getage=tvage.text.toString().toInt()
            val weightvalue=tvweight.text.toString().toInt()
            val getheight=binding.seekbar.progress.toString().toInt()
            intent.putExtra("mage",getage)
            intent.putExtra("mweight",weightvalue)
            intent.putExtra("mheight",getheight)
            startActivity(intent)
        }
    }
}