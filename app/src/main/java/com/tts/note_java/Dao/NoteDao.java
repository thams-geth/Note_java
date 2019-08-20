package com.tts.note_java.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.tts.note_java.Entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);


    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("SELECT * FROM note_table WHERE priority DESC")
    LiveData<List<Note>> getAllNotes();
}
