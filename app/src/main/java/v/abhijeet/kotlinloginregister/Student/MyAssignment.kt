package v.abhijeet.kotlinloginregister.Student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast

import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_my_assignment.*

import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
import v.abhijeet.kotlinloginregister.DatabaseModel.Taskdetails
import v.abhijeet.kotlinloginregister.R

class MyAssignment : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var database2 : FirebaseDatabase
    private lateinit var reference2: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_assignment)

        val strUser: String? = intent.getStringExtra("firebasekey")

        val firekey = strUser.toString()

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Students").child(firekey)

       database2 = FirebaseDatabase.getInstance()
       reference2 = database2.getReference("Task").child(firekey)




        getData()


    }

    private fun getData() {





       reference.addListenerForSingleValueEvent( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val value  = dataSnapshot.getValue(Studentdetails::class.java)


                if (value != null)
                {

                    val name : String = value.studentname

                    et_name.setText(name)




                    Toast.makeText(baseContext, " SucessFull!!",
                        Toast.LENGTH_SHORT).show()

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {

                Toast.makeText(baseContext, "TAsk Failed!!!!",
                    Toast.LENGTH_SHORT).show()

            }
           })


        reference2.addListenerForSingleValueEvent( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val tasks = dataSnapshot.getValue(Taskdetails::class.java)




                if(tasks != null)
                {
                    val sub1 : String = tasks.subject1
                    val sub2: String = tasks.subject2
                    val sub3 : String = tasks.subject3
                    val sub4 : String = tasks.subject4

                    et_sub1.setText(sub1)
                    et_sub2.setText(sub2)
                    et_sub3.setText(sub3)
                    et_sub4.setText(sub4)

                    Toast.makeText(baseContext, "TAsk Successfull!!!!",
                        Toast.LENGTH_SHORT).show()

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

                Toast.makeText(baseContext, "TAsk Failed!!!!",
                    Toast.LENGTH_SHORT).show()

            }
        })


    }

}