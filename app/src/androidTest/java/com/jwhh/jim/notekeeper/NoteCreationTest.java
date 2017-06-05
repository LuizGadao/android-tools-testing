package com.jwhh.jim.notekeeper;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by Luiz on 02/06/17.
 */
@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {

    private static DataManager sDatamanager;

    @BeforeClass
    public static void setUpClass(){
        sDatamanager = DataManager.getInstance();
    }

    @Rule
    public ActivityTestRule<NoteListActivity> mActivityActivityTestRule =
            new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote(){

        CourseInfo mCourseInfo = sDatamanager.getCourse("java_lang");

        String title = "note title";
        String subTitle = "note text description";

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.spinner_courses)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class), equalTo(mCourseInfo))).perform(click());
        onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(containsString(mCourseInfo.getTitle()))));

        onView(withId(R.id.text_note_title)).perform(typeText(title)).check(matches(withText(containsString(title))));
        onView(withId(R.id.text_note_text)).perform(typeText(subTitle), closeSoftKeyboard());

        onView(withId(R.id.text_note_text)).check(matches(withText(containsString(subTitle))));

        pressBack();

        int indexNote = sDatamanager.getNotes().size() - 1;
        NoteInfo mNoteInfoCreted = sDatamanager.getNotes().get(indexNote);
        assertEquals(mCourseInfo, mNoteInfoCreted.getCourse());
        assertEquals(title, mNoteInfoCreted.getTitle());
        assertEquals(subTitle, mNoteInfoCreted.getText());
    }
}