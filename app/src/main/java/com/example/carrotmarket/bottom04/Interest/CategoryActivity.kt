package com.example.carrotmarket.bottom04.Interest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.carrotmarket.R
import com.example.carrotmarket.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryBinding

    var checkDi = true
    var checkAn = true
    var checkBe = true
    var checkBo = true
    var checkBuy = true
    var checkCh = true
    var checkCl = true
    var checkEtc = true
    var checkFo = true
    var checkFu = true
    var checkGame = true
    var checkLife = true
    var checkMan = true
    var checkMu = true
    var checkPl = true
    var checkSp = true
    var checkSu = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkDi()
        checkAn()
        checkBe()
        checkBo()
        checkBuy()
        checkCh()
        checkCl()
        checkEtc()
        checkFo()
        checkFu()
        checkGame()
        checkLife()
        checkMan()
        checkMu()
        checkPl()
        checkSp()
        checkSu()

        back()
    }

    fun checkDi(){
        checkDi = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkDi","").toBoolean()

        if (checkDi == true)binding.ivDigital.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivDigital.setImageResource(R.drawable.ic_check_circle)

        binding.ivDigital.setOnClickListener {
            if (checkDi == true){
                binding.ivDigital.setImageResource(R.drawable.ic_check_circle)
                checkDi = false
            }else {
                binding.ivDigital.setImageResource(R.drawable.ic_checked_circle)
                checkDi = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkDi", checkDi.toString()).apply()

        }
    }

    fun checkAn(){
        checkAn = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkAn","").toBoolean()

        if (checkAn == true)binding.ivAnimal.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivAnimal.setImageResource(R.drawable.ic_check_circle)

        binding.ivAnimal.setOnClickListener {
            if (checkAn == true){
                binding.ivAnimal.setImageResource(R.drawable.ic_check_circle)
                checkAn = false
            }else {
                binding.ivAnimal.setImageResource(R.drawable.ic_checked_circle)
                checkAn = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkAn", checkAn.toString()).apply()

        }
    }

    fun checkBe(){
        checkBe = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkBe","").toBoolean()

        if (checkBe == true)binding.ivBeauty.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivBeauty.setImageResource(R.drawable.ic_check_circle)

        binding.ivBeauty.setOnClickListener {
            if (checkBe == true){
                binding.ivBeauty.setImageResource(R.drawable.ic_check_circle)
                checkBe = false
            }else {
                binding.ivBeauty.setImageResource(R.drawable.ic_checked_circle)
                checkBe = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkBe", checkBe.toString()).apply()

        }
    }

    fun checkBo(){
        checkBo = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkBo","").toBoolean()

        if (checkBo == true)binding.ivBook.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivBook.setImageResource(R.drawable.ic_check_circle)

        binding.ivBook.setOnClickListener {
            if (checkBo == true){
                binding.ivBook.setImageResource(R.drawable.ic_check_circle)
                checkBo = false
            }else {
                binding.ivBook.setImageResource(R.drawable.ic_checked_circle)
                checkBo = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkBo", checkBo.toString()).apply()

        }
    }

    fun checkBuy(){
        checkBuy = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkBuy","").toBoolean()

        if (checkBuy == true)binding.ivBuy.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivBuy.setImageResource(R.drawable.ic_check_circle)

        binding.ivBuy.setOnClickListener {
            if (checkBuy == true){
                binding.ivBuy.setImageResource(R.drawable.ic_check_circle)
                checkBuy = false
            }else {
                binding.ivBuy.setImageResource(R.drawable.ic_checked_circle)
                checkBuy = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkBuy", checkBuy.toString()).apply()

        }
    }

    fun checkCh(){
        checkCh = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkCh","").toBoolean()

        if (checkCh == true)binding.ivChild.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivChild.setImageResource(R.drawable.ic_check_circle)

        binding.ivChild.setOnClickListener {
            if (checkCh == true){
                binding.ivChild.setImageResource(R.drawable.ic_check_circle)
                checkCh = false
            }else {
                binding.ivChild.setImageResource(R.drawable.ic_checked_circle)
                checkCh = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkCh", checkCh.toString()).apply()

        }
    }

    fun checkCl(){
        checkCl = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkCl","").toBoolean()

        if (checkCl == true)binding.ivClothing.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivClothing.setImageResource(R.drawable.ic_check_circle)

        binding.ivClothing.setOnClickListener {
            if (checkCl == true){
                binding.ivClothing.setImageResource(R.drawable.ic_check_circle)
                checkCl = false
            }else {
                binding.ivClothing.setImageResource(R.drawable.ic_checked_circle)
                checkCl = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkCl", checkCl.toString()).apply()

        }
    }

    fun checkEtc(){
        checkEtc = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkEtc","").toBoolean()

        if (checkEtc == true)binding.ivEtc.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivEtc.setImageResource(R.drawable.ic_check_circle)

        binding.ivEtc.setOnClickListener {
            if (checkEtc == true){
                binding.ivEtc.setImageResource(R.drawable.ic_check_circle)
                checkEtc = false
            }else {
                binding.ivEtc.setImageResource(R.drawable.ic_checked_circle)
                checkEtc = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkEtc", checkEtc.toString()).apply()

        }
    }

    fun checkFo(){
        checkFo = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkFo","").toBoolean()

        if (checkFo == true)binding.ivFood.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivFood.setImageResource(R.drawable.ic_check_circle)

        binding.ivFood.setOnClickListener {
            if (checkFo == true){
                binding.ivFood.setImageResource(R.drawable.ic_check_circle)
                checkFo = false
            }else {
                binding.ivFood.setImageResource(R.drawable.ic_checked_circle)
                checkFo = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkFo", checkFo.toString()).apply()

        }
    }

    fun checkFu(){
        checkFu = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkFu","").toBoolean()

        if (checkFu == true)binding.ivFurniture.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivFurniture.setImageResource(R.drawable.ic_check_circle)

        binding.ivFurniture.setOnClickListener {
            if (checkFu == true){
                binding.ivFurniture.setImageResource(R.drawable.ic_check_circle)
                checkFu = false
            }else {
                binding.ivFurniture.setImageResource(R.drawable.ic_checked_circle)
                checkFu = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkFu", checkFu.toString()).apply()

        }
    }

    fun checkGame(){
        checkGame = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkGame","").toBoolean()

        if (checkGame == true)binding.ivGame.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivGame.setImageResource(R.drawable.ic_check_circle)

        binding.ivGame.setOnClickListener {
            if (checkGame == true){
                binding.ivGame.setImageResource(R.drawable.ic_check_circle)
                checkGame = false
            }else {
                binding.ivGame.setImageResource(R.drawable.ic_checked_circle)
                checkGame = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkGame", checkGame.toString()).apply()

        }
    }

    fun checkLife(){
        checkLife = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkLife","").toBoolean()

        if (checkLife == true)binding.ivLife.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivLife.setImageResource(R.drawable.ic_check_circle)

        binding.ivLife.setOnClickListener {
            if (checkLife == true){
                binding.ivLife.setImageResource(R.drawable.ic_check_circle)
                checkLife = false
            }else {
                binding.ivLife.setImageResource(R.drawable.ic_checked_circle)
                checkLife = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkLife", checkLife.toString()).apply()

        }
    }

    fun checkMan(){
        checkMan = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkMan","").toBoolean()

        if (checkMan == true)binding.ivMan.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivMan.setImageResource(R.drawable.ic_check_circle)

        binding.ivMan.setOnClickListener {
            if (checkMan == true){
                binding.ivMan.setImageResource(R.drawable.ic_check_circle)
                checkMan = false
            }else {
                binding.ivMan.setImageResource(R.drawable.ic_checked_circle)
                checkMan = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkMan", checkMan.toString()).apply()

        }
    }

    fun checkMu(){
        checkMu = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkMu","").toBoolean()

        if (checkMu == true)binding.ivMusic.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivMusic.setImageResource(R.drawable.ic_check_circle)

        binding.ivMusic.setOnClickListener {
            if (checkMu == true){
                binding.ivMusic.setImageResource(R.drawable.ic_check_circle)
                checkMu = false
            }else {
                binding.ivMusic.setImageResource(R.drawable.ic_checked_circle)
                checkMu = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkMu", checkMu.toString()).apply()

        }
    }

    fun checkPl(){
        checkPl = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkPl","").toBoolean()

        if (checkPl == true)binding.ivPlant.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivPlant.setImageResource(R.drawable.ic_check_circle)

        binding.ivPlant.setOnClickListener {
            if (checkPl == true){
                binding.ivPlant.setImageResource(R.drawable.ic_check_circle)
                checkPl = false
            }else {
                binding.ivPlant.setImageResource(R.drawable.ic_checked_circle)
                checkPl = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkPl", checkPl.toString()).apply()

        }
    }

    fun checkSp(){
        checkSp = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkSp","").toBoolean()

        if (checkSp == true)binding.ivSports.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivSports.setImageResource(R.drawable.ic_check_circle)

        binding.ivSports.setOnClickListener {
            if (checkSp == true){
                binding.ivSports.setImageResource(R.drawable.ic_check_circle)
                checkSp = false
            }else {
                binding.ivSports.setImageResource(R.drawable.ic_checked_circle)
                checkSp = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkSp", checkSp.toString()).apply()

        }
    }

    fun checkSu(){
        checkSu = getSharedPreferences("category", AppCompatActivity.MODE_PRIVATE).getString("checkSu","").toBoolean()

        if (checkSu == true)binding.ivStuff.setImageResource(R.drawable.ic_checked_circle)
        else binding.ivStuff.setImageResource(R.drawable.ic_check_circle)

        binding.ivStuff.setOnClickListener {
            if (checkSu == true){
                binding.ivStuff.setImageResource(R.drawable.ic_check_circle)
                checkSu = false
            }else {
                binding.ivStuff.setImageResource(R.drawable.ic_checked_circle)
                checkSu = true
            }

            getSharedPreferences("category", MODE_PRIVATE).edit().putString("checkSu", checkSu.toString()).apply()

        }
    }

    fun back(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

}