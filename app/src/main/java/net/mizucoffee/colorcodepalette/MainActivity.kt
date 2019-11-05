package net.mizucoffee.colorcodepalette

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.ClipDescription
import android.content.Context
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Gravity
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onChanged = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                colorConvert(color11, seekBar1.progress + 60, seekBar2.progress + 60, seekBar3.progress + 60)
                colorConvert(color12, seekBar1.progress + 30, seekBar2.progress + 30, seekBar3.progress + 30)
                colorConvert(color13, seekBar1.progress, seekBar2.progress, seekBar3.progress)
                colorConvert(color14, seekBar1.progress - 30, seekBar2.progress - 30, seekBar3.progress - 30)
                colorConvert(color15, seekBar1.progress - 60, seekBar2.progress - 60, seekBar3.progress - 60)

                colorConvert(color21, seekBar1.progress + 70, seekBar2.progress + 70, seekBar3.progress + 70)
                colorConvert(color22, seekBar1.progress + 40, seekBar2.progress + 40, seekBar3.progress + 40)
                colorConvert(color23, seekBar1.progress + 10, seekBar2.progress + 10, seekBar3.progress + 10)
                colorConvert(color24, seekBar1.progress - 20, seekBar2.progress - 20, seekBar3.progress - 20)
                colorConvert(color25, seekBar1.progress - 50, seekBar2.progress - 50, seekBar3.progress - 50)
            }
            override fun onStartTrackingTouch(sb: SeekBar) {}
            override fun onStopTrackingTouch(sb: SeekBar) {}
        }
        val onClick = View.OnClickListener {
            val mimeType = arrayOfNulls<String>(1)
            mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST
            val cd = ClipData(ClipDescription("text_data", mimeType),  ClipData.Item((it as TextView).text))
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(cd)
            Toast.makeText(applicationContext, "${it.text}をコピーしました", Toast.LENGTH_SHORT).show()
        }

        seekBar1.setOnSeekBarChangeListener(onChanged)
        seekBar2.setOnSeekBarChangeListener(onChanged)
        seekBar3.setOnSeekBarChangeListener(onChanged)
        color11.setOnClickListener(onClick)
        color12.setOnClickListener(onClick)
        color13.setOnClickListener(onClick)
        color14.setOnClickListener(onClick)
        color15.setOnClickListener(onClick)
        color21.setOnClickListener(onClick)
        color22.setOnClickListener(onClick)
        color23.setOnClickListener(onClick)
        color24.setOnClickListener(onClick)
        color25.setOnClickListener(onClick)
        onChanged.onProgressChanged(seekBar1,0,false)
    }

    fun colorConvert(view: TextView, i1: Int, i2: Int, i3: Int) {
        view.setBackgroundColor(Color.rgb(check255(i1), check255(i2), check255(i3)))
        view.text = "#${Integer.toHexString(check255(i1))}${Integer.toHexString(check255(i2))}${Integer.toHexString(check255(i3))}"
    }

    private fun check255(i: Int): Int {
        return if(i > 255) 255 else if(i < 0) 0 else i
    }
}