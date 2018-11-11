



package com.example.rajne.snapchat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.example.rajne.snapchat.R.string.email



class MainActivity : Activity() {

    var EmaileditText: EditText? = null
    var PasswordeditText: EditText? = null

    val mAuth = FirebaseAuth.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EmaileditText = findViewById(R.id.EmaileditText);


        PasswordeditText = findViewById(R.id.PasswordeditText);


        if(mAuth.currentUser != null){
            logIn()
        }
    }
      fun goClick(view: View) {

          // check if we can log in the user
          mAuth.signInWithEmailAndPassword(EmaileditText?.text.toString(),PasswordeditText?.text.toString())
                  .addOnCompleteListener(this) { task ->
                      if (task.isSuccessful) {
                         logIn()
                      } else {
                          //Sign up the user
                          mAuth.createUserWithEmailAndPassword(EmaileditText?.text.toString(),PasswordeditText?.text.toString()).addOnCompleteListener(this){task ->

                              if(task.isSuccessful){
                                  // add to database
                                  logIn()
                              }else{
                                  Toast.makeText(this,"Login failed.Try again",Toast.LENGTH_SHORT).show();
                              }
                          }
                      }


                  }



      }
    fun logIn(){
        val intent = Intent(this,SnapActivity::class.java)
        startActivity(intent)
    }
}
