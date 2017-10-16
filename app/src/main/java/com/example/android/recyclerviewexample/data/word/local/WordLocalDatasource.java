package com.example.android.recyclerviewexample.data.word.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.data.word.WordDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 13/10/2017.
 */

public class WordLocalDatasource extends SQLiteOpenHelper  implements WordDataSource {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_WORDS = "words";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "name";

    public WordLocalDatasource(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void getAllWords(LoadAllWordsCallback callback) {
        List<Word> wordList = new ArrayList<Word>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(Integer.parseInt(cursor.getString(0)));
                word.setWord(cursor.getString(1));
                wordList.add(word);
            } while (cursor.moveToNext());
            callback.onAllWordLoaded(wordList);
        }else{
            callback.onDataNotAvailable();
        }
        
    }

    @Override
    public void addWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getWord()); // Contact Name
        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }
}
