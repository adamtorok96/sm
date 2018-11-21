package hu.sandorszilard.spendingmonitor.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.db.models.Income;
import hu.sandorszilard.spendingmonitor.db.models.Outgoing;

public class StatisticsFragment extends Fragment {
    private static StatisticsFragment instance = null;

    private TextView tvIncome, tvOutgoing, tvBalance;

    public static StatisticsFragment getInstance() {
        if( instance == null ) {
            instance = new StatisticsFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_fragment, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tvIncome = view.findViewById(R.id.tvIncome);
        tvOutgoing = view.findViewById(R.id.tvOutgoing);
        tvBalance = view.findViewById(R.id.tvBalance);

        loadData();
    }

    public void loadData() {
        long income = Income.getSum();
        long outgoing = Outgoing.getSum();
        long balance = income - outgoing;

        tvIncome.setText(String.format(Locale.getDefault(), "%d Ft", income));
        tvOutgoing.setText(String.format(Locale.getDefault(), "%d Ft", outgoing));
        tvBalance.setText(String.format(Locale.getDefault(), "%d Ft", balance));

        if( balance == 0 ) {
            tvBalance.setTextColor(Color.BLACK);
        } else if( balance > 0 ) {
            tvBalance.setTextColor(Color.GREEN);
        } else {
            tvBalance.setTextColor(Color.RED);
        }
    }
}
