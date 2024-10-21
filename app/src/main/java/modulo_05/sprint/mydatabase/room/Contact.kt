package modulo_05.sprint.mydatabase.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import modulo_05.sprint.mydatabase.model.LocalDateConverter

@Entity(tableName = "contacts")
@TypeConverters(LocalDateConverter::class)
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name ="name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "profile_picture")
    val profilePicture: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String
)