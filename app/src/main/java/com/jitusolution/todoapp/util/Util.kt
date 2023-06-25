import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jitusolution.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo " + "ADD COLUMN priority INTEGER DEFAULT 3 not null")
//        database.execSQL("INSERT INTO todo(title, notes, priority) " + "VALUES('Study hard', 'Play harder',3)"

    }
}

val MIGRATION_2_3 = object:Migration(2, 3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 not null")
//is_done menggunakan integer dikarenakan room sqlite tidak memiliki boolean namun hanya diketahui
// jika angka 1 maka true dan 0 yaitu false pada sqlite commands sehingga digunakan integer

    }
}

