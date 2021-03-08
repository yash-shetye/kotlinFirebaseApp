package v.abhijeet.kotlinloginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()


        val register = findViewById<Button>(R.id.register_btn)
        register.setOnClickListener { teacherregsiter() }



    }

    private fun teacherregsiter() {
        var Teachername = findViewById<EditText>(R.id.teacherName)
        var teacherid = findViewById<EditText>(R.id.teacher_id)
        var Email = findViewById<EditText>(R.id.teacher_email)
        var Password = findViewById<EditText>(R.id.teacher_Password)
        var confirmPass = findViewById<EditText>(R.id.teacher_confirmPassword)

        val email: String = Email.text.toString()
        val password: String = Password.text.toString()

        if(Teachername.text.toString().isEmpty()){
            Teachername.error = "Please Enter Name"
            Teachername.requestFocus()
            return
        }
        if(teacherid.text.toString().isEmpty()){
            teacherid.error = "Please enter ID"
            teacherid.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()){
            Email.error = "Invalid format"
            Email.requestFocus()
            return
        }
        if(Password.text.toString().isEmpty()){
            Password.error = "Please enter password"
            Password.requestFocus()
            return
        }
        if(Password.text.toString()!=confirmPass.text.toString()){
            confirmPass.error = "Password Does Not matched"
            confirmPass.requestFocus()
            return
        }


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, " Registration Sucessfull",
                            Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    Toast.makeText(baseContext, " Login Sucessfull",
                            Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(baseContext, "Authentication failed."  + task.getException(),
                        Toast.LENGTH_SHORT).show()

                }


            }


    }
}