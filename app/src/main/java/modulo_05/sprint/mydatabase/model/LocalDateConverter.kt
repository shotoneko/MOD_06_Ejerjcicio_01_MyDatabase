package modulo_05.sprint.mydatabase.model

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(localDate: LocalDate): String {
        return localDate.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String): LocalDate {
        return dateString.toLocalDate()
    }
}