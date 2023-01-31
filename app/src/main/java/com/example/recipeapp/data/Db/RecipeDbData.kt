package com.example.recipeapp.data.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="BookMark")
data class RecipeDbData (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id:Int,

    @ColumnInfo(name="title")
    val title:String
    )


