package modulo_05.sprint.mydatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name ="name") val name: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "profile_picture") val profilePicture: String?,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: LocalDate?
)