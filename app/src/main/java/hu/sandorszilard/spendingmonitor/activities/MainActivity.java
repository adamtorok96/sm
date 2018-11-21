package hu.sandorszilard.spendingmonitor.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.adapters.ViewPagerAdapter;
import hu.sandorszilard.spendingmonitor.fragments.AddNewIncomeFragment;
import hu.sandorszilard.spendingmonitor.fragments.OutgoingsFragment;
import hu.sandorszilard.spendingmonitor.fragments.AddNewOutgoingFragment;
import hu.sandorszilard.spendingmonitor.fragments.IncomesFragment;
import hu.sandorszilard.spendingmonitor.interfaces.OnItemAddedListener;

public class MainActivity extends AppCompatActivity implements OnItemAddedListener {

    private static final String DIALOG_FRAGMENT_TAG = "dialog";

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateDialog();
            }
        });

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void showCreateDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment prev = fm.findFragmentByTag(DIALOG_FRAGMENT_TAG);

        if ( prev != null ) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);

        DialogFragment newFragment;

        if( getCurrentPageId() == 0 )
            newFragment = AddNewOutgoingFragment.newInstance(this);
        else
            newFragment = AddNewIncomeFragment.newInstance(this);

        newFragment.show(ft, DIALOG_FRAGMENT_TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getCurrentPageId() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public void onAdded() {
        if( getCurrentPageId() == 0 )
            OutgoingsFragment.getInstance().reload();
        else
            IncomesFragment.getInstance().reload();
    }
}
