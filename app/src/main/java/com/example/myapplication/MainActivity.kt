package com.example.myapplication

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.myapplication.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val request = DownloadManager.Request(Uri.parse("https://www.dropbox.com/s/v457fzh5xyihrys/Terms%20of%20comprehensive%20banking%20services.pdf?dl=1"))
        request.setTitle("services.pdf")
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "services.pdf"
        )
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)


        binding.apply {

            val file = File(Environment.DIRECTORY_DOWNLOADS + "services.pdf")
            pdfViewer.fromFile(file)
                //.enableSwipe(true)
                //.enableDoubletap(true)
                //.enableAnnotationRendering(false)
                .load()

            //pdfViewer.fromAsset("Kotlin.pdf").load()
        }


    }
}