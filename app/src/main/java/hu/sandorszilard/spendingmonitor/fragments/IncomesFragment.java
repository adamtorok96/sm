package hu.sandorszilard.spendingmonitor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.adapters.IncomesAdapter;

public class IncomesFragment extends Fragment {
    private static IncomesFragment instance = null;

    private RecyclerView mRecyclerView;
    private IncomesAdapter mIncomesAdapter;

    public static IncomesFragment getInstance() {
        if( instance == null ) {
            instance = new IncomesFragment();
        }

        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.incomes_fragment, container, false);

        initRecyclerView();

        return mRecyclerView;
    }

    private void initRecyclerView() {
        mIncomesAdapter = new IncomesAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mIncomesAdapter);
    }

    public void reload() {
        mIncomesAdapter.reload();
    }
}
