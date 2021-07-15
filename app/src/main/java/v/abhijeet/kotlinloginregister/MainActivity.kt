package v.abhijeet.kotlinloginregister

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
import v.abhijeet.kotlinloginregister.Student.StudentLogin
import v.abhijeet.kotlinloginregister.Teacher.AddStudent
import v.abhijeet.kotlinloginregister.Teacher.AddStudentandTask


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()




        val loginteacher = findViewById(R.id.Loginasteacher) as Button
        val loginstudent = findViewById<Button>(R.id.loginasstudent)
        val newUser = findViewById<TextView>(R.id.register_txt)

        newUser.setOnClickListener{ gotoStudent()}

        loginteacher.setOnClickListener { loginTechers() }
        loginstudent.setOnClickListener { loginStudents() }
        addstudent.setOnClickListener{ addStudents()}
    }

    private fun addStudents() {
        val intent = Intent(this, AddStudent::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()

      //  val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

  /*  private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser!=null) {
            startActivity(Intent(this, AddStudentandTask::class.java))
            finish()
        }else{
            Toast.makeText(baseContext,"Login Failed.",Toast.LENGTH_LONG).show()
        }

    }*/


    private fun gotoStudent() {
        val intent = Intent(this, Registration::class.java)
        startActivity(intent)
    }

    private fun loginTechers() {
        var Email = findViewById<EditText>(R.id.editTextEmail)
        var Password = findViewById<EditText>(R.id.editTextPassword)


        val email: String = Email.text.toString()
        val password: String = Password.text.toString()

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


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    Toast.makeText(
                        baseContext, "Login Sucessfull.",
                        Toast.LENGTH_SHORT
                    ).show()



                    startActivity(Intent(this, AddStudentandTask::class.java))
                } else {


                    Toast.makeText(
                        baseContext, "Authentication failed." + task.getException(),
                        Toast.LENGTH_LONG
                    ).show()

                }

                // ...
            }



    }

    private fun loginStudents() {

        val intent = Intent(this, StudentLogin::class.java)
        startActivity(intent)

    }
}