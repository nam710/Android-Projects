package com.room.todolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> notelist;

    public NoteRepo(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDao = noteDataBase.noteDao();
        notelist = noteDao.getAllData();
    }

    public void insertData(Note note){new InsertTask(noteDao).execute(note);}
    public void editData(Note note){new EditTask(noteDao).execute(note);}
    public void deleteData(Note note){new DeleteTask(noteDao).execute(note);}
    public LiveData<List<Note>> getAllData(){
        return notelist;
    }

    private static class InsertTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class DeleteTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class EditTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public EditTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.edit(notes[0]);
            return null;
        }
    }
}
