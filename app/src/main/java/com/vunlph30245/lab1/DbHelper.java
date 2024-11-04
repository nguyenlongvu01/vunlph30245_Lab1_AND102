package com.vunlph30245.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DbHelper extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "SanphamDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột
    public static final String TABLE_SANPHAM = "Sanpham";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "type";

    // Câu lệnh tạo bảng
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SANPHAM + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_CONTENT + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TYPE + " TEXT" +
                    ");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);


        insertSanpham(db, "San pham 1", "abc", "2023-01-01", "Loại A");
        insertSanpham(db, "San pham 2", "jqk", "2023-02-01", "Loại B");
        insertSanpham(db, "San pham 3", "xyz", "2023-03-01", "Loại C");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SANPHAM);
        onCreate(db);
    }

    // Phương thức hỗ trợ thêm dữ liệu
    private void insertSanpham(SQLiteDatabase db, String title, String content, String date, String type) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TYPE, type);
        db.insert(TABLE_SANPHAM, null, values);
    }
}
