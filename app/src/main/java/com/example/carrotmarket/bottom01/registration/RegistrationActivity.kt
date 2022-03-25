package com.example.carrotmarket.bottom01.registration

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.CursorLoader
import com.example.carrotmarket.databinding.ActivityRegistrationBinding
import okhttp3.MultipartBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class registrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var viewModel : registrationViewModel
    lateinit var dialog: AlertDialog
    var category:String? = null

    var adapter:RegistrationAdapter? = null
    var result = ""
//    var uri = ""
    var path:File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(registrationViewModel::class.java)
        setContentView(binding.root)

        categoryBtn()
        setObserver()
        registrationToServerBtn()
        pictureBtn()
    }

    private fun setObserver() {
        viewModel.idResult.observe(this, Observer {
            if (it.status == "success") {
                finish()
            }
        })
    }

    fun registrationToServerBtn() {
        binding.completion.setOnClickListener {
            // 방어코드 : Exception 방어
            if (binding.etComment.text.toString() != null) {
                viewModel.registrationToServer(binding.etComment.text.toString(), category!! ,binding.etPrice.text.toString(),binding.etTittle.text.toString(),path)
            }
        }
    }

    fun pictureBtn(){
        binding.picture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)  //

        if (resultCode != Activity.RESULT_OK) return

        when (requestCode) {
            10 -> {
                data?:return
                val uri = data.data

                binding.pictureList.setImageURI(uri)

                val pathString = getRealPathFromUri(uri!!)!!
                Log.d("path", "pathString : ${pathString}")
               path = pathString
                Log.d("path", "path : ${path}")
//                adapter = RegistrationAdapter(uri)
//                binding.pictureList.adapter = adapter
            }

            else -> Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRealPathFromUri(uri: Uri): File? {

        Log.d("uriResult", "uri : $uri")
        val context = applicationContext
        val contentResolver = context.contentResolver ?: return null

        // Create file path inside app's data dir
        val filePath = (context.applicationInfo.dataDir + File.separator
                + System.currentTimeMillis())
        val file = File(filePath)
        try {
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("uriResult", "file : ${file.getAbsolutePath()}")
        }
        return file
    }

    fun categoryBtn(){
        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        val items = arrayOf("디지털기기","생활가전","가구/인테리어","유아동","생활/가공식품","유아도서","스포츠/레저","여성잡화",
            "여성의류","남성패션/잡화","게임/취미","뷰티/미용","반려동물용품","도서/티켓/음반","식물","기타중고물품","삽니다")

        binding.choiceCategory.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                builder.setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, which: Int) {
                        category = items[which]
                        binding.category.text = items[which]
                    }
                })
                dialog = builder.create()
                dialog.setCanceledOnTouchOutside(true)
                dialog.show()
            }
        })
    }

}

