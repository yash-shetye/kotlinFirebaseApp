package v.abhijeet.kotlinloginregister.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_add_task.*
import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
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


        var c1 = findViewById<CheckBox>(R.id.ch_subject1)
        var c2 = findViewById<CheckBox>(R.id.ch_subject2)
        var c3 = findViewById<CheckBox>(R.id.ch_subject3)
        var c4 = findViewById<CheckBox>(R.id.ch_subject4)

        var subject11 = c1.text.toString().trim()
        var subject22 = c2.text.toString().trim()
        var subject33 = c3.text.toString().trim()
        var subject44 = c4.text.toString().trim()



        var strUser: String? = intent.getStringExtra("firebasekey1")

        var firekey = strUser.toString()


        var task = Taskdetails()

       // var model = Taskdetails(subject1 ,subject2,subject3,subject4)
       reference.child(firekey).setValue(task)


        if(c1.isChecked){

            task.subject1 = subject11
            reference.child(firekey).setValue(task)
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c2.isChecked){

            task.subject2  = subject22
            reference.child(firekey).setValue(task)
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c3.isChecked){

            task.subject3 = subject33
            reference.child(firekey).setValue(task)
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }
        if(c4.isChecked){

            task.subject4 = subject44
            reference.child(firekey).setValue(task)
            Toast.makeText(baseContext, "Task added.",
                    Toast.LENGTH_LONG).show()

        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}