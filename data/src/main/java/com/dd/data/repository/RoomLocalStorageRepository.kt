package com.dd.data.repository

import android.content.Context
import androidx.room.Room
import com.dd.data.BuildConfig
import com.dd.data.db.MakalDatabase
import com.dd.domain.model.ResponseCategoryModel
import com.dd.domain.model.ResponseMakalModel
import com.dd.domain.repository.LocalStorageRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SupportFactory
import java.io.File
import java.io.IOException
import java.util.logging.Logger


class RoomLocalStorageRepository(
        private val context: Context,
        private val logger: Logger) : LocalStorageRepository {

    private val dbSecretKey = BuildConfig.DB_SECRET_KEY
    private val passphrase: ByteArray = SQLiteDatabase.getBytes(dbSecretKey.toCharArray())
    private val factory = SupportFactory(passphrase)

    val db: MakalDatabase by lazy {
        val build = Room.databaseBuilder(
                context.applicationContext,
                MakalDatabase::class.java,
                BuildConfig.DB_NAME)
        build.build()
    }

    init {
        encrypt(context.applicationContext, BuildConfig.DB_NAME, dbSecretKey)
    }

    @Throws(IOException::class)
    fun encrypt(ctxt: Context, dbName: String?, passphrase: String?) {
        try {
            val originalFile = ctxt.getDatabasePath(dbName)
            if (originalFile.exists()) {
                SQLiteDatabase.loadLibs(context)
                val newFile = File.createTempFile("sqlcipherutils", "tmp", ctxt.cacheDir)
                var db = SQLiteDatabase.openDatabase(originalFile.absolutePath, "", null, SQLiteDatabase.OPEN_READWRITE)
                db.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';",
                        newFile.absolutePath, passphrase))
                db.rawExecSQL("SELECT sqlcipher_export('encrypted')")
                db.rawExecSQL("DETACH DATABASE encrypted;")
                val version = db.version
                db.close()
                db = SQLiteDatabase.openDatabase(newFile.absolutePath, passphrase, null, SQLiteDatabase.OPEN_READWRITE)
                db.version = version
                db.close()
                originalFile.delete()
                newFile.renameTo(originalFile)
            }
        } catch (e: SQLiteException) {
            println(e.message)
        }
    }



    override fun getAllCategories(responseCategoryModel: ResponseCategoryModel) {
    }

    override fun getAllMakals(responseMakalModel: ResponseMakalModel) {
    }
}