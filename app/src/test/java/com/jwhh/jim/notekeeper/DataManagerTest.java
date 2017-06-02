package com.jwhh.jim.notekeeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Luiz on 02/06/17.
 */
public class DataManagerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createNewNote() throws Exception {
        DataManager mDataManager = DataManager.getInstance();
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

}