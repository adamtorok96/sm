package hu.sandorszilard.spendingmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.adapters.OutgoingsAdapter;
import hu.sandorszilard.spendingmonitor.db.models.Outgoing;

public class OutgoingsFragment extends Fragment {
    private static OutgoingsFragment instance = null;

    private RecyclerView mRecyclerView;
    private OutgoingsAdapter mOutgoingsAdapter;

    private GraphView mGraphView;

    String[] mCategories;

    public static OutgoingsFragment getInstance() {
        if( instance == null ) {
            instance = new OutgoingsFragment();
        }

        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCategories = getResources().getStringArray(R.array.categories);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outgoings_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.rvOutgoings);
        mGraphView = view.findViewById(R.id.gvOutgoings);

        initRecyclerView();
        initGraph();

        return view;
    }

    private void initRecyclerView() {
        mOutgoingsAdapter = new OutgoingsAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mOutgoingsAdapter);
    }

    private void initGraph() {
        loadGraphData();

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(mGraphView);
        staticLabelsFormatter.setHorizontalLabels(mCategories);

        mGraphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

    private void loadGraphData() {
        DataPoint[] dataPoints = new DataPoint[mCategories.length];

        for(int i = 0; i < mCategories.length; i++) {
            dataPoints[i] = new DataPoint(i, Outgoing.getSumOfCategory(i));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);

        series.setSpacing(50);
        series.setDrawValuesOnTop(true);

        mGraphView.addSeries(series);
    }

    public void reload() {
        mOutgoingsAdapter.reload();

        loadGraphData();
    }
}
