package v.abhijeet.kotlinloginregister.Student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_student_login.*
import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
import v.abhijeet.kotlinloginregister.R


class StudentLogin : AppCompatActivity() {
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        val loginBtn = findViewById<Button>(R.id.studentlogin_btn)
        database = FirebaseDatabase.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        reference = database.getReference("Students").child(user.uid)

        loginBtn.setOnClickListener { gotoStudentlogin() }
    }

    private fun gotoStudentlogin() {
        val studentname = studentloginname.text.toString().trim()
        val id = studentloginid.text.toString().trim()


        if(studentname.isEmpty() || id.isEmpty()){
            Toast.makeText(baseContext, "All fields are required",
                Toast.LENGTH_LONG).show()
        }
        isEmailExist(studentname, id)



    }

    private fun isEmailExist(studentname: String , id:String){
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                val list = ArrayList<Studentdetails>()
                var isemailexist = false

                for(postsnapshot in dataSnapshot.children)
                {
                    val value = postsnapshot.getValue(Studentdetails::class.java)

                    if(value!!.studentname == studentname  && value!!.id == id)
                    {
                        isemailexist = true
                    }
                    list.add(value!!)
                }
                if(isemailexist)
                {
                    Toast.makeText(baseContext, " Login SucessFull",
                        Toast.LENGTH_SHORT).show()

                   // startActivity(Intent(applicationContext,MyAssignment::class.java))

                    val intent = Intent(applicationContext, MyAssignment::class.java)
                    intent.putExtra("firebasekey", id)

                    startActivity(intent)


                }else{
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}