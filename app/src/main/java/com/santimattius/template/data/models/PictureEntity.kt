package com.santimattius.template.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.santimattius.template.domain.entities.Picture

@Entity(tableName = "picture")
data class PictureEntity(
    @PrimaryKey @ColumnInfo(name = "id") override val id: String = "",
    @ColumnInfo(name = "author") override val author: String = "",
    @ColumnInfo(name = "width") override val width: Int = 0,
    @ColumnInfo(name = "download_url") override val downloadUrl: String = "",
    @ColumnInfo(name = "url") override val url: String = "",
    @ColumnInfo(name = "height") override val height: Int = 0
) : Picture