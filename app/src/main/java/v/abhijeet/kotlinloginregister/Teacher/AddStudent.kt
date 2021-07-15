package v.abhijeet.kotlinloginregister.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_student.*
import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
import v.abhijeet.kotlinloginregister.R
import v.abhijeet.kotlinloginregister.Student.StudentLogin
import java.lang.ref.Reference

class AddStudent : AppCompatActivity() {
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)


        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Students")

        val addstudent = findViewById<Button>(R.id.btn_addstudent)
        addstudent.setOnClickListener { sendData() }

        val addtaskbutton = findViewById<Button>(R.id.btn_addTaskss)
        addtaskbutton.setOnClickListener {
           //checkExistingUser()
            gotoasddtask() }




    }

   /* private fun checkExistingUser() {
        reference.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
              if(!snapshot.exists())
              {
                  sendData()
              }
                else{
                  var idd = et_id.text.toString().trim()

                  val child = snapshot!!.children

                  child.forEach{


                  }
              }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }*/


    private fun gotoasddtask() {
        startActivity(Intent(this,AddTask::class.java))

    }

    private fun sendData(){



        var studentname = et_studentname.text.toString().trim()
        var standard  = et_standard.text.toString().trim()
        var div = et_div.text.toString().trim()
        var id = et_id.text.toString().trim()

        if(studentname.isEmpty()){

            et_studentname.error = "Please Enter Name"
            et_studentname.requestFocus()
            return

        }
        if(standard.isEmpty()){
            et_standard.error = "Please Enter Standard"
            et_standard.requestFocus()
            return
        }
        if(div.isEmpty()){
            et_div.error = "Please Enter Standard"
            et_div.requestFocus()
            return
        }
        if(id.isEmpty()){
            et_id.error = "Please Enter Standard"
            et_id.requestFocus()
            return
        }

        var model = Studentdetails(studentname,standard,div,id)

       // var user_class = Studentdetails()
       // val user = FirebaseAuth.getInstance().signInAnonymously()
        val user = FirebaseAuth.getInstance().currentUser

        //val ref = FirebaseDatabase.getInstance().getReference("Students")

        //ref.child(user.uid).setValue(user_class)


      //var sid = reference.push().setValue(id)
        if(user != null  ){
         //   reference.child("9MHhf68N2T").child(user.toString()).child(id).setValue(model)
            reference.child(user.uid).child(id).setValue(model)
        }else{
            reference.child(id).setValue(model)
        }



        val intent = Intent(this, AddTask::class.java)
       intent.putExtra("firebasekey1", id)

        startActivity(intent)

        Toast.makeText(baseContext, "Student Added.",
            Toast.LENGTH_SHORT).show()

    }

}