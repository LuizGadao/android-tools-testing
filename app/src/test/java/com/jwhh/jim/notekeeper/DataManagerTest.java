package com.jwhh.jim.notekeeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Luiz on 02/06/17.
 */
public class DataManagerTest {
    private DataManager mDataManager;

    @Before
    public void setUp() throws Exception {
        mDataManager = DataManager.getInstance();
        mDataManager.getNotes().clear();
        mDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() throws Exception {
        CourseInfo mCourse = mDataManager.getCourse("android_async");
        String mNoteTitle = "Test note title";
        String mNoteText = "This is the body text of my test note";

        int mNoteIndex = mDataManager.createNewNote();
        NoteInfo mNewNote = mDataManager.getNotes().get(mNoteIndex);
        mNewNote.setCourse(mCourse);
        mNewNote.setTitle(mNoteTitle);
        mNewNote.setText(mNoteText);

        NoteInfo mCompareNote = mDataManager.getNotes().get(mNoteIndex);
        assertEquals(mCompareNote.getCourse(), mCourse);
        assertEquals(mCompareNote.getTitle(), mNoteTitle);
        assertEquals(mCompareNote.getText(), mNoteText);
    }

    @Test
    public void findSimilarNotes() throws Exception {
        CourseInfo mCourse = mDataManager.getCourse("android_async");
        String mNoteTitle = "Test note title";
        String mNoteText = "This is the body text of my test note";
        String mNoteText2 = "This is the body text of my test note 2";

        int mNoteIndex = mDataManager.createNewNote();
        NoteInfo mNewNote = mDataManager.getNotes().get(mNoteIndex);
        mNewNote.setCourse(mCourse);
        mNewNote.setTitle(mNoteTitle);
        mNewNote.setText(mNoteText);

        int mNoteIndex2 = mDataManager.createNewNote();
        NoteInfo mSecondNote = mDataManager.getNotes().get(mNoteIndex2);
        mSecondNote.setCourse(mCourse);
        mSecondNote.setTitle(mNoteTitle);
        mSecondNote.setText(mNoteText2);

        assertEquals(mNoteIndex, mDataManager.findNote(mNewNote));
        assertEquals(mNoteIndex2, mDataManager.findNote(mSecondNote));
    }

}