package s1092796.pu.edu.com.example.a2022test

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.*
import s1092796.pu.edu.com.example.a2022test.databinding.ActivityMainBinding
@GlideModule
public final class MyAppGlideModule : AppGlideModule()


class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView
    lateinit var mysv:MySurfaceView
    lateinit var binding: ActivityMainBinding
    var flag:Boolean = false
    lateinit var job: Job
    lateinit var elmer : ImageView
    lateinit var fly1:Fly

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img.setOnClickListener {
            if (flag) {
                flag = false
                binding.img.setImageResource(R.drawable.start)
                job.cancel()
            } else {
                flag = true
                binding.img.setImageResource(R.drawable.stop)
                job = GlobalScope.launch(Dispatchers.Main) {
                    while (flag) {
                        delay(10)
                        binding.mysv.fly1.update()

                        var canvas: Canvas = binding.mysv.surfaceHolder.lockCanvas()
                        binding.mysv.drawSomething(canvas)
                        binding.mysv.surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }

            }
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }
}