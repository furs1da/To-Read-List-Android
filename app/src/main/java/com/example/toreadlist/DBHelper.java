package com.example.toreadlist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "reading_list_db";
    //database version
    private static final int DB_VERSION = 1;
    //table name
    private static final String TB_NAME = "library";

    private static final String ID_COL = "id";

    private static final String IS_COMPLETED_COL = "isCompleted";

    private static final String TITLE_COL = "title";
    private static final String DESCRIPTION_COL = "description";
    private static final String PUBLISHER_COL = "publisher";
    private static final String PUBLISHED_DATE_COL = "publisherDate";
    private static final String PAGE_COUNT_COL = "pageCount";
    private static final String IMAGE_URL_COL = "imageUrl";
    private static final String BOOK_DETAILS_URL_COL = "bookDetails";
    private static final String LANGUAGE_COL = "language";

    //constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create a table
        String query = "CREATE TABLE " + TB_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE_COL + " TEXT," +
                DESCRIPTION_COL + " TEXT," +
                PUBLISHER_COL + " TEXT," +
                PUBLISHED_DATE_COL + " TEXT," +
                PAGE_COUNT_COL + " INTEGER," +
                IMAGE_URL_COL + " TEXT," +
                BOOK_DETAILS_URL_COL + " TEXT," +
                LANGUAGE_COL + " TEXT," +
                IS_COMPLETED_COL + " INTEGER)";
        //execute Query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //drop table
        String query = "drop table " + TB_NAME;
        db.execSQL(query);
        //recreate it
        onCreate(db);
    }

    public void addBookToReadingList(BookItem bookItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, bookItem.getTitle());
        values.put(DESCRIPTION_COL, bookItem.getDescription());
        values.put(PUBLISHER_COL, bookItem.getPublisher());
        values.put(PUBLISHED_DATE_COL, bookItem.getPublishedDate());
        values.put(PAGE_COUNT_COL, bookItem.getPageCount());
        values.put(IMAGE_URL_COL, bookItem.getImageUrl());
        values.put(BOOK_DETAILS_URL_COL, bookItem.getBookDetailsLink());
        values.put(LANGUAGE_COL, bookItem.getLanguage());
        values.put(IS_COMPLETED_COL, bookItem.getIsCompleted());

        db.insert(TB_NAME, null, values);
        db.close();
    }

    public boolean deleteAllBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        //no where clause no arguments of where
        db.delete(TB_NAME, "", new String[]{});
        db.close();
        return true;
    }

    public boolean deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //no where clause no arguments of where
        db.delete(TB_NAME, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return true;
    }


    public boolean updateBook(BookItem bookItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, bookItem.getTitle());
        values.put(DESCRIPTION_COL, bookItem.getDescription());
        values.put(PUBLISHER_COL, bookItem.getPublisher());
        values.put(PUBLISHED_DATE_COL, bookItem.getPublishedDate());
        values.put(PAGE_COUNT_COL, bookItem.getPageCount());
        values.put(IMAGE_URL_COL, bookItem.getImageUrl());
        values.put(BOOK_DETAILS_URL_COL, bookItem.getBookDetailsLink());
        values.put(LANGUAGE_COL, bookItem.getLanguage());
        values.put(IS_COMPLETED_COL, bookItem.getIsCompleted());

        db.update(TB_NAME,values,"id=?",new String[]{String.valueOf(bookItem.getId())});
        db.close();
        return true;
    }


    @SuppressLint("Range")
    public ArrayList<BookItem> readBookList() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + TB_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<BookItem> bookItemArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorBooks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                bookItemArrayList.add(new BookItem(
                        cursorBooks.getString(cursorBooks.getColumnIndex(TITLE_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(DESCRIPTION_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHER_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHED_DATE_COL)),
                        cursorBooks.getInt(cursorBooks.getColumnIndex(PAGE_COUNT_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(IMAGE_URL_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(BOOK_DETAILS_URL_COL)),
                        cursorBooks.getString(cursorBooks.getColumnIndex(LANGUAGE_COL)),
                        cursorBooks.getInt(cursorBooks.getColumnIndex(IS_COMPLETED_COL)) == 1 ? true : false,
                        cursorBooks.getInt(cursorBooks.getColumnIndex(ID_COL))));

            } while (cursorBooks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorBooks.close();
        return bookItemArrayList;
    }

    @SuppressLint("Range")
    public BookItem getBookById(Integer id) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TB_NAME + " WHERE "+ ID_COL + " =" + id;


        Cursor cursorBooks = db.rawQuery(selectQuery, null);
        BookItem bookItem = new BookItem();
        // on below line we are creating a cursor with query to read data from database.
        if (cursorBooks != null  && cursorBooks.getCount() > 0) {
            cursorBooks.moveToFirst();
            bookItem.setId(cursorBooks.getInt(cursorBooks.getColumnIndex(ID_COL)));
            bookItem.setTitle(cursorBooks.getString(cursorBooks.getColumnIndex(TITLE_COL)));
            bookItem.setDescription(cursorBooks.getString(cursorBooks.getColumnIndex(DESCRIPTION_COL)));
            bookItem.setPublisher(cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHER_COL)));
            bookItem.setPublishedDate(cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHED_DATE_COL)));
            bookItem.setPageCount(cursorBooks.getInt(cursorBooks.getColumnIndex(PAGE_COUNT_COL)));
            bookItem.setImageUrl(cursorBooks.getString(cursorBooks.getColumnIndex(IMAGE_URL_COL)));
            bookItem.setBookDetailsLink(cursorBooks.getString(cursorBooks.getColumnIndex(BOOK_DETAILS_URL_COL)));
            bookItem.setLanguage(cursorBooks.getString(cursorBooks.getColumnIndex(LANGUAGE_COL)));
            bookItem.setIsCompleted(cursorBooks.getInt(cursorBooks.getColumnIndex(IS_COMPLETED_COL)) == 1 ? true : false);
        }

        // and returning our array list.
        cursorBooks.close();
        return bookItem;
    }

    @SuppressLint("Range")
    public BookItem getBookByTitle(String title) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TB_NAME + " WHERE "+ TITLE_COL + " =" + "'" + title + "'";


        Cursor cursorBooks = db.rawQuery(selectQuery, null);
        BookItem bookItem = new BookItem();
        // on below line we are creating a cursor with query to read data from database.
        if (cursorBooks != null && cursorBooks.getCount() > 0) {
            cursorBooks.moveToFirst();
            bookItem.setId(cursorBooks.getInt(cursorBooks.getColumnIndex(ID_COL)));
            bookItem.setTitle(cursorBooks.getString(cursorBooks.getColumnIndex(TITLE_COL)));
            bookItem.setDescription(cursorBooks.getString(cursorBooks.getColumnIndex(DESCRIPTION_COL)));
            bookItem.setPublisher(cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHER_COL)));
            bookItem.setPublishedDate(cursorBooks.getString(cursorBooks.getColumnIndex(PUBLISHED_DATE_COL)));
            bookItem.setPageCount(cursorBooks.getInt(cursorBooks.getColumnIndex(PAGE_COUNT_COL)));
            bookItem.setImageUrl(cursorBooks.getString(cursorBooks.getColumnIndex(IMAGE_URL_COL)));
            bookItem.setBookDetailsLink(cursorBooks.getString(cursorBooks.getColumnIndex(BOOK_DETAILS_URL_COL)));
            bookItem.setLanguage(cursorBooks.getString(cursorBooks.getColumnIndex(LANGUAGE_COL)));
            bookItem.setIsCompleted(cursorBooks.getInt(cursorBooks.getColumnIndex(IS_COMPLETED_COL)) == 1 ? true : false);
        }

        // and returning our array list.
        cursorBooks.close();
        return bookItem;
    }

}
