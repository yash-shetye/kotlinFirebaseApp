package v.abhijeet.kotlinloginregister.DatabaseModel

class Taskdetails() {

     var subject1: String = ""
     var subject2: String = ""
     var subject3: String = ""
     var subject4: String = ""

    constructor(subject1: String,subject2: String,subject3: String,subject4: String) : this()
    {
        this.subject1=subject1
        this.subject2=subject2
        this.subject3=subject3
        this.subject4=subject4
    }

}