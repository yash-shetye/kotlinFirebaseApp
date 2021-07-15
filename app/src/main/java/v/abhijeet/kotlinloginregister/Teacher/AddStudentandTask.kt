package v.abhijeet.kotlinloginregister.Teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import v.abhijeet.kotlinloginregister.R

class AddStudentandTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_studentand_task)

        val addstudent = findViewById<Button>(R.id.addstudent_btn)
        val addtask = findViewById<Button>(R.id.addtask_btn)
        val viewStud = findViewById<Button>(R.id.viewassign_btn)

        addstudent.setOnClickListener { gotoaddStudent() }
        addtask.setOnClickListener { gotoaddTask() }
        viewStud.setOnClickListener { viewStudent() }
    }

    private fun viewStudent() {

    }

    private fun gotoaddTask() {
        val intent = Intent(this,AddTask::class.java)
        startActivity(intent)
    }

    private fun gotoaddStudent() {
        val intent = Intent(this,AddStudent::class.java)
        startActivity(intent)

    }
}