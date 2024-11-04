package com.vunlph30245.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ToDoDAO {

    private DbHelper dbHelper;

    public ToDoDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanphamModel> getListToDo() {
        ArrayList<SanphamModel> listToDo = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String query = "SELECT * FROM " + DbHelper.TABLE_SANPHAM;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CONTENT));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_DATE));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TYPE));


                listToDo.add(new SanphamModel(title, content, date, type));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listToDo;
    }


    public void addToDo(SanphamModel sanphamModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", sanphamModel.getTitle());
        values.put("content", sanphamModel.getContent());
        values.put("date", sanphamModel.getDate());
        values.put("type", sanphamModel.getType());
        db.insert("sanpham", null, values);
        db.close();
    }

    // Phương thức để cập nhật công việc
    public void updateToDo(SanphamModel sanphamModel) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", sanphamModel.getTitle());
        values.put("content", sanphamModel.getContent());
        values.put("date", sanphamModel.getDate());
        values.put("type", sanphamModel.getType());
        db.update("sanpham", values, "title = ?", new String[]{sanphamModel.getTitle()});
        db.close();
    }


    public void deleteToDo(String title) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("sanpham", "title = ?", new String[]{title});
        db.close();
    }
}

