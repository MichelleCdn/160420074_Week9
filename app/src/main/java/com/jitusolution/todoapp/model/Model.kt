package com.jitusolution.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var is_done:Int=0
//is_done menggunakan integer dikarenakan room sqlite tidak memiliki boolean namun hanya diketahui
// jika angka 1 maka true dan 0 yaitu false pada sqlite commands sehingga digunakan integer
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}


