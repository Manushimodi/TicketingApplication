package com.example.nisargdoshi.ticktingapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class databasehandler(context: Context, name: String?,
                      factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION){


    fun findProduct(productname: String) {
        val query = "SELECT * FROM $TABLE_PRODUCTS WHERE $COLUMN_PRODUCTNAME =  \"$productname\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)


        // var product: Product? = null

        cursor.moveToFirst()
        for(i in 0 until  cursor.count)
        {
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val quantity = Integer.parseInt(cursor.getString(2))
            cursor.moveToNext()
        }

        Log.d("","")
        if (cursor.moveToFirst()) {


            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val quantity = Integer.parseInt(cursor.getString(2))
            //  product = Product(id, name, quantity)
            cursor.close()
        }

        db.close()
        // return product
    }


    fun addProduct() {

        val values = ContentValues()
        values.put(databasehandler.COLUMN_PRODUCTNAME, "dfghjk")
        values.put(databasehandler.COLUMN_ID, 1)
        values.put(databasehandler.COLUMN_QUANTITY, 10)


        val db = this.writableDatabase

        db.insert(databasehandler.TABLE_PRODUCTS, null, values)
        db.close()
    }


    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")")
        db!!.execSQL(CREATE_PRODUCTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS)
        onCreate(db)
    }




    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "productDB.db"
        val TABLE_PRODUCTS = "products"

        val COLUMN_ID = "_id"
        val COLUMN_PRODUCTNAME = "productname"
        val COLUMN_QUANTITY = "quantity"
    }
}

