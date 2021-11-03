package com.example.google

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.get
import com.example.google.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Hide keyboard on Enter key
        binding.PersonName.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.username.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.lastname.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextTextConfirmPassword2.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
        binding.editTextTextPassword.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }

        //Action Listener for the next Button
        binding.registerButton.setOnClickListener {





            // if the user enter all the info , the password is the same print the info
            //and check the email pattern
            //or show warning message
            if (isTextEmpty() && passwordCheck() && emailPattern(binding.username.editText?.text.toString())&&passLength()) {
                Toast.makeText(applicationContext, getString(R.string.registration_succeeded), Toast.LENGTH_SHORT).show()

            } else if (!passwordCheck()) {
                Toast.makeText(applicationContext, getString(R.string.shouldmatch), Toast.LENGTH_SHORT).show()
            }else if(!emailPattern(binding.username.editText?.text.toString())){
                binding.username.hint = "Enter valid email!!"
                Toast.makeText(applicationContext, getString(R.string.registration_notsucceeded), Toast.LENGTH_SHORT).show()

            } else if(!passLength()){
                Toast.makeText(applicationContext, getString(R.string.pass), Toast.LENGTH_SHORT).show()

            }
        }
//for checkboox

binding.ch.setOnClickListener {
    if (binding.ch.isChecked) {
        binding.editTextTextPassword.editText?.setTransformationMethod(  null )
        binding.editTextTextConfirmPassword2.editText?.setTransformationMethod(null)

    }else {
        binding.editTextTextPassword.editText?.setTransformationMethod(  PasswordTransformationMethod.getInstance() )
        binding.editTextTextConfirmPassword2.editText?.setTransformationMethod(PasswordTransformationMethod.getInstance())

    }
}
        binding.usecrrent.setOnClickListener {
            Toast.makeText(applicationContext, getString(R.string.link), Toast.LENGTH_SHORT).show()

        }
        binding.useemail.setOnClickListener {
            Toast.makeText(applicationContext, getString(R.string.link), Toast.LENGTH_SHORT).show()

        }

    }




     fun passLength(): Boolean {
         return binding.editTextTextConfirmPassword2.editText?.length()?.toInt()!! >= 8 && passwordCheck()

     }

    //fun to check the email pattern
    fun emailPattern(email: String): Boolean{
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[com]+"
        return email.matches(emailPattern.toRegex())
    }

    //fun to check if the passwords match or not
    fun passwordCheck(): Boolean {

        return binding.editTextTextPassword.editText?.text.toString().equals(binding.editTextTextConfirmPassword2.editText?.text.toString())
    }

    //fun to check if the user enter the info or not
    fun isTextEmpty(): Boolean {

        val nameCheck = isNameEmpty(binding.PersonName.editText?.text.toString().isNotEmpty())
        val lastNameCheck = isLastNameEmpty(binding.lastname.editText?.text.toString().isNotEmpty())
        val emailCheck = isEmailEmpty(binding.username.editText?.text.toString().isNotEmpty())
        val pass1Check = ispassEmpty(binding.editTextTextPassword.editText?.text.toString().isNotEmpty())
        val pass2Check = ispassEmpty(binding.editTextTextConfirmPassword2.editText?.text.toString().isNotEmpty())

        return nameCheck && emailCheck  && pass1Check && pass2Check && lastNameCheck
    }


    //fun to check if the user enter the email or not
    fun isEmailEmpty(email :Boolean): Boolean {
        return if (!email){
            binding.username.hint = "Enter Your Email!"
            false
        }else true
    }

    //fun to check if the user enter the name or not
    fun isNameEmpty(name : Boolean): Boolean {
        return if (!name){
            binding.PersonName.hint = "Enter Your First name!"
            false
        }else true
    }

    fun isLastNameEmpty(name : Boolean): Boolean {
        return if (!name){
            binding.lastname.hint = "Enter Your Last name!"
            false
        }else true
    }

    // fun to check if the user enter the Password or not
    fun ispassEmpty(pass : Boolean): Boolean {
        return if (!pass){
            binding.editTextTextPassword.hint = "Enter Your Password!"
            false
        }else true
    }

    // fun to check if the user enter the Password or not
    fun ispass2Empty(pass : Boolean): Boolean {
        return if (!pass){
            binding.editTextTextConfirmPassword2.hint = "Enter Your Password!"
            false
        }else true
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }




}