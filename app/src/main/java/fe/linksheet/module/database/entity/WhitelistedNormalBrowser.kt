package fe.linksheet.module.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import fe.linksheet.module.database.dao.base.PackageEntity
import fe.linksheet.module.database.dao.base.PackageEntityCreator
import fe.linksheet.module.repository.WhitelistedNormalBrowsersRepository

@Entity(tableName = "whitelisted_browser", indices = [Index("packageName", unique = true)])
data class WhitelistedNormalBrowser(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") val id: Int = 0,
    override val packageName: String
) : WhitelistedBrowser<WhitelistedNormalBrowser>(packageName) {

    companion object Creator : PackageEntityCreator<WhitelistedNormalBrowser> {
        override fun createInstance(packageName: String) = WhitelistedNormalBrowser(
            packageName = packageName
        )
    }
}