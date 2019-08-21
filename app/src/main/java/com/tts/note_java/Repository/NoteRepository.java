package com.tts.note_java.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tts.note_java.Dao.NoteDao;
import com.tts.note_java.Database.NoteDatabase;
import com.tts.note_java.Entity.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        new insertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new updateAsyncTask(noteDao).execute(note);

    }

    public void delete(Note note) {
        new deleteAsyncTask(noteDao).execute(note);

    }

    public void deleteAllNotes() {
        new deleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class insertAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        insertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... note) {
            noteDao.insert(note[0]);
            return null;
        }
    }

    public static class updateAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        updateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... note) {
            noteDao.update(note[0]);
            return null;
        }
    }

    public static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        deleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... note) {
            noteDao.delete(note[0]);
            return null;
        }
    }

    public static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;

        deleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
