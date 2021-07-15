package v.abhijeet.kotlinloginregister.Teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import v.abhijeet.kotlinloginregister.R

class viewStudents : AppCompatActivity() {
    lateinit var mrecyclerview : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        mrecyclerview = findViewById(R.id.rview)
    }
}