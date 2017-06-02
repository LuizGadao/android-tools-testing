package com.jwhh.jim.notekeeper;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Luiz on 02/06/17.
 */
@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {

    @Rule
    public ActivityTestRule<NoteListActivity> mActivityActivityTestRule =
            new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote(){
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_note_title)).perform(typeText("note title"));
        onView(withId(R.id.text_note_text)).perform(typeText("note text description"), closeSoftKeyboard());
    }
}