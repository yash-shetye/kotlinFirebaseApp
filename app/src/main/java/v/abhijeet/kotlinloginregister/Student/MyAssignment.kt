package v.abhijeet.kotlinloginregister.Student


import android.app.ProgressDialog
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_my_assignment.*

import v.abhijeet.kotlinloginregister.DatabaseModel.Studentdetails
import v.abhijeet.kotlinloginregister.DatabaseModel.Taskdetails
import v.abhijeet.kotlinloginregister.R

class MyAssignment : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var database2 : FirebaseDatabase
    private lateinit var reference2: DatabaseReference



    val pdf : Int = 0

    lateinit var uri: Uri
    lateinit var mStorage: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_assignment)

        val strUser: String? = intent.getStringExtra("firebasekey")

        val firekey = strUser.toString()
        val user = FirebaseAuth.getInstance().currentUser


        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Students").child(user.uid).child(firekey)

       database2 = FirebaseDatabase.getInstance()
       reference2 = database2.getReference("Task").child(user.uid).child(firekey)


        getData()
        val pdfBtn=findViewById<Button>(R.id.btn_upload)

        mStorage=FirebaseStorage.getInstance().getReference("Uploads").child(user.uid).child(firekey)

        pdfBtn.setOnClickListener(View.OnClickListener {
               val intent = Intent()
            intent.type="application/pdf"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), pdf)

        })



    }
    private fun getData() {


        reference.addListenerForSingleValueEvent( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(postsnapshot in dataSnapshot.children) {
                    val value = dataSnapshot.getValue(Studentdetails::class.java)


                    if (value != null) {

                        val name: String = value.studentname

                        et_name.setText(name)




                        Toast.makeText(
                            baseContext, " SucessFull!!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uriTxt=findViewById<TextView>(R.id.uriTxt)

        if(resultCode== RESULT_OK){
            if(requestCode==pdf){
                uri= data!!.data!!
                uriTxt.text=uri.toString()
                upload()



            }
        }

        super.onActivityResult(requestCode, resultCode, data)


    }



    private fun upload() {
        if(uri != null)
        {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

           // var pdfpath = FirebaseStorage.getInstance().reference.child("Assignments")
            mStorage.putFile(uri)
                .addOnSuccessListener {p0 ->
                    pd.dismiss()
                    Toast.makeText(baseContext, "File uploaded",
                        Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener{p0 ->
                     pd.dismiss()
                    Toast.makeText(baseContext, p0.message,
                        Toast.LENGTH_SHORT).show()

                }
                .addOnProgressListener {p0 ->
                    var progress :Double = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }

    }




}