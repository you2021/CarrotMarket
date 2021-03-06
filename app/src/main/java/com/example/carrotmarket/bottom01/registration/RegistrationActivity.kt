package com.example.carrotmarket.bottom01.registration

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrotmarket.databinding.ActivityRegistrationBinding
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor
import java.io.FileInputStream


class registrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var registrationViewModel: RegistrationViewModel
    lateinit var imageViewModel: RegistrationImageViewModel
    var imageArr : ArrayList<String>? = null

    var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        imageViewModel = ViewModelProvider(this).get(RegistrationImageViewModel::class.java)
        setContentView(binding.root)

        setObserver()
        registrationToServerBtn()
        categoryBtn()

        launcher = registerForActivityResult(StartActivityForResult()) {
            var adapter: RegistrationAdapter? = null
            val clipData = it.data!!.clipData

            val multipartArr = ArrayList<MultipartBody.Part>()
            val uriArr = ArrayList<Uri>()

            if (clipData == null){
                val uri = it.data!!.data
                multipartArr.add(uriToMultipart(uri!!))
                uriArr.add(uri)
            }else{
                val clipDataCount = it.data!!.clipData!!.itemCount
                clipData.let { clipData ->
                    for (i in 0 until clipDataCount!!) {
                        val selectedImageUri = clipData.getItemAt(i).uri

                        multipartArr.add(uriToMultipart(selectedImageUri!!))
                        uriArr.add(selectedImageUri)
                    }
                }
            }

            adapter = RegistrationAdapter(uriArr)
            binding.pictureList.adapter = adapter

            imageViewModel.imageToServer(multipartArr)
            Toast.makeText(this, "????????? ????????????????????????.", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onStart() {
        super.onStart()
        pictureBtn()
    }


    fun uriToMultipart(uri: Uri): MultipartBody.Part {
        val resolver = this.contentResolver
        resolver.openFileDescriptor(uri, "r").use {
            val fileDescriptor: FileDescriptor = it!!.getFileDescriptor()
            val fis = FileInputStream(fileDescriptor)
            val swapStream = ByteArrayOutputStream()
            val buff = ByteArray(1024 * 4) //buff???????????????????????????????????????

            var rc = 0
            while (fis.read(buff, 0, 100).also { rc = it } > 0) {
                swapStream.write(buff, 0, rc)
            }

            val fileName = fileName(uri)
            val requestBody: RequestBody = RequestBody.create(MultipartBody.FORM, swapStream.toByteArray())
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("image","_$fileName" , requestBody)

            return body
        }
    }

    fun fileName(uri:Uri) : String{
        val cursor = contentResolver.query(
            uri,
             null,
            null,
            null,
            null
        )

        val imgFileName: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
        cursor.moveToFirst()
        Log.d("fileName", imgFileName.toString())
        return cursor.getString(imgFileName)

    }

    private fun setObserver() {
        registrationViewModel.result.observe(this, Observer {
            if (it.status == "success") {
                finish()
            }
        })

        imageViewModel.result.observe(this, {
            imageArr = it
            Log.d("imageChange", "${imageArr}")
        })
    }

    fun registrationToServerBtn() {
        binding.completion.setOnClickListener {
            registrationViewModel.registrationToServer(
                binding.etComment.text.toString(),
                binding.category.text.toString(),
                binding.etPrice.text.toString(),
                binding.etTittle.text.toString(),
                imageArr!!
            )

        }
    }

    fun pictureBtn() {
        binding.picture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT

            launcher!!.launch(intent)

        }
    }

    fun categoryBtn() {
        var dialog: AlertDialog? = null
        var category: String? = null
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val items = arrayOf(
            "???????????????", "????????????", "??????/????????????", "?????????", "??????/????????????", "????????????", "?????????/??????", "????????????",
            "????????????", "????????????/??????", "??????/??????", "??????/??????", "??????????????????", "??????/??????/??????", "??????", "??????????????????", "?????????"
        )  // db

        binding.choiceCategory.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                builder.setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, which: Int) {
                        category = items[which]
                        binding.category.text = items[which]
                    }
                })
                dialog = builder.create()
                dialog!!.setCanceledOnTouchOutside(true)
                dialog!!.show()
            }
        })
    }
}

