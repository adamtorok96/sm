package hu.sandorszilard.spendingmonitor.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.sandorszilard.spendingmonitor.fragments.OutgoingsFragment;
import hu.sandorszilard.spendingmonitor.fragments.IncomesFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0
                ? OutgoingsFragment.getInstance()
                : IncomesFragment.getInstance()
        ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0
                ? "Kiadás"
                : "Bevétel"
        ;
    }
}
