package com.example.jaker.wildlife2;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import java.lang.reflect.Method;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest{

    private Fragment fragment;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initialise()
    {
        Bundle bundle  =  new Bundle();
        bundle.putBoolean("EDIT", false);

        fragment = new AddEditFragment();
        fragment.setArguments(bundle);
         mActivityRule.getActivity().getSupportFragmentManager().beginTransaction()
                 .replace(R.id.fragment_container, fragment).commit();
    }

    @Test
    public void test1()
    {
        String nameInput1 = "23547";
        //String catInput1 = "vr45334";
        String amountInput1 = "647";
        String locINput1 = "sgtgehth";
        String actInput1 = "gerthet";




        //onData(withId(R.id.addFab)).inAdapterView(withId().describeMismatch();  ).perform(click());
        //onView(withId(R.id.addFab)).perform(click());
        try
        {
            Thread.sleep(1500);
        }
        catch (Exception e)
        {

        }
        onView(withId(R.id.addEditNameET)).perform(typeText(nameInput1));
        onView(withId(R.id.addEditAmountET)).perform(typeText(amountInput1));
        onView(withId(R.id.addEditLocET)).perform(typeText(locINput1));
        onView(withId(R.id.addEditActET)).perform(typeText(actInput1)).perform(closeSoftKeyboard());


        onView(withId(R.id.addEditSubmitBtn)).perform(click());


    }

    @Test
    public void test2()
    {
        String nameInput1 = "different";
        //String catInput1 = "vr45334";
        String amountInput1 = "mate";
        String locINput1 = "sgtgehth";
        String actInput1 = "gerthet";




        //onData(withId(R.id.addFab)).inAdapterView(withId().describeMismatch();  ).perform(click());
        //onView(withId(R.id.addFab)).perform(click());
        try
        {
            Thread.sleep(1500);
        }
        catch (Exception e)
        {

        }
        onView(withId(R.id.addEditNameET)).perform(typeText(nameInput1));
        onView(withId(R.id.addEditAmountET)).perform(typeText(amountInput1));
        onView(withId(R.id.addEditLocET)).perform(typeText(locINput1));
        onView(withId(R.id.addEditActET)).perform(typeText(actInput1)).perform(closeSoftKeyboard());


        onView(withId(R.id.addEditSubmitBtn)).perform(click());

        try
        {
            Thread.sleep(3000);
        }
        catch (Exception e)
        {

        }


    }


    /*@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();

        //assertEquals("com.example.jaker.wildlife2", appContext.getPackageName());
    }*/
}
