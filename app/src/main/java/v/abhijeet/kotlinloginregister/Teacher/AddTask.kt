package v.abhijeet.kotlinloginregister.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import v.abhijeet.kotlinloginregister.DatabaseModel.Taskdetails
import v.abhijeet.kotlinloginregister.MainActivity
import v.abhijeet.kotlinloginregister.R

class AddTask : AppCompatActivity() {
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)




        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Task")



        val addtasks = findViewById<Button>(R.id.addtask)
        addtasks.setOnClickListener { putData() }

    }

    private fun putData() {


        val c1 = findViewById<CheckBox>(R.id.ch_subject1)
        val c2 = findViewById<CheckBox>(R.id.ch_subject2)
        val c3 = findViewById<CheckBox>(R.id.ch_subject3)
        val c4 = findViewById<CheckBox>(R.id.ch_subject4)

        val subject11 = c1.text.toString().trim()
        val subject22 = c2.text.toString().trim()
        val subject33 = c3.text.toString().trim()
        val subject44 = c4.text.toString().trim()



        val strUser: String? = intent.getStringExtra("firebasekey1")

        val firekey = strUser.toString()


        val task = Taskdetails()

        val user = FirebaseAuth.getInstance().currentUser
       // var model = Taskdetails(subject1 ,subject2,subject3,subject4)

        if(user != null  ){
            reference.child(user.uid).child(firekey).setValue(task)
        }else{
            reference.child(firekey).setValue(task)
        }

        if(c1.isChecked){

            task.subject1 = subject11
            if(user != null  ){
                reference.child(user.uid).child(firekey).setValue(task)
            }else{
                reference.child(firekey).setValue(task)
            }
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c2.isChecked){

            task.subject2  = subject22
            if(user != null  ){
                reference.child(user.uid).child(firekey).setValue(task)
            }else{
                reference.child(firekey).setValue(task)
            }
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c3.isChecked){

            task.subject3 = subject33
            if(user != null  ){
                reference.child(user.uid).child(firekey).setValue(task)
            }else{
                reference.child(firekey).setValue(task)
            }
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c4.isChecked){

            task.subject4 = subject44
            if(user != null  ){
                reference.child(user.uid).child(firekey).setValue(task)
            }else{
                reference.child(firekey).setValue(task)
            }
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}