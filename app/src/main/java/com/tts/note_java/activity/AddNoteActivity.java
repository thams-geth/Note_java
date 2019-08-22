package com.tts.note_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.tts.note_java.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_PRIORITY = "priority";
    EditText etTitle;
    EditText etDesc;
    NumberPicker npPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        npPriority = findViewById(R.id.npPriprity);
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);

        npPriority.setMinValue(1);
        npPriority.setMaxValue(10);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDesc.setText(intent.getStringExtra(EXTRA_DESC));
            npPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else
            setTitle("Add Note");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        int priority = npPriority.getValue();

        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please insert a title and desc", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_DESC, desc);
            intent.putExtra(EXTRA_PRIORITY, priority);
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                intent.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
