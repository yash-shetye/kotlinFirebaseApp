package v.abhijeet.kotlinloginregister.DatabaseModel

class Studentdetails () {

    var studentname: String = ""
    var standard: String = ""
    var div: String = ""
    var id: String = ""

    constructor(studentname: String,standard: String,div: String,id: String) : this()
    {
        this.studentname=studentname
        this.standard=standard
        this.div=div
        this.id=id
    }

}


//var studentname:String , var standard:String , var div:String , var id:String