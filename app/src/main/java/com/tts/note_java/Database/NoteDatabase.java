package com.tts.note_java.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tts.note_java.Dao.NoteDao;
import com.tts.note_java.Entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateAsyncTask(instance).execute();
        }
    };

    private static class populateAsyncTask extends AsyncTask<Void, Void, Void> {

        NoteDao noteDao;

        private populateAsyncTask(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1", "desc 1", 1));
            noteDao.insert(new Note("title 2", "desc 2", 2));
            noteDao.insert(new Note("title 3", "desc 3", 3));
            return null;
        }
    }
}
