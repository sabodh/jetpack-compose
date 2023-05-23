 

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VCategoryItem(
    @PrimaryKey(autoGenerate = true) var uid: Int=0,
    @ColumnInfo(name = "VC_Code") val VC_Code: Int=0,
    @ColumnInfo(name = "VC_Name") val VC_Name: String? ="",
    @ColumnInfo(name = "VC_NameEng") val VC_NameEng: String?=""
)