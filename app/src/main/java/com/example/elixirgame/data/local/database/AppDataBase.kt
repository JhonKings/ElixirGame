package com.example.elixirgame.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elixirgame.data.local.dao.VideoGameDao
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [VideoGameResponse::class, VideoGameDetailResponse::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun videoGameDAO(): VideoGameDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "elixir_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}