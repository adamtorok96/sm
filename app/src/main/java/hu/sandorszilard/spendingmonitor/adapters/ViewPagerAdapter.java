package hu.sandorszilard.spendingmonitor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.sandorszilard.spendingmonitor.fragments.OutgoingsFragment;
import hu.sandorszilard.spendingmonitor.fragments.IncomesFragment;
import hu.sandorszilard.spendingmonitor.fragments.StatisticsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private Fragment getFragment(int pos) {
        switch (pos) {
            case 1:
                return OutgoingsFragment.getInstance();

            case 2:
                return IncomesFragment.getInstance();

            default:
                return StatisticsFragment.getInstance();
        }
    }
}
