package hu.sandorszilard.spendingmonitor.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.db.models.Outgoing;

public class OutgoingsAdapter extends RecyclerView.Adapter<OutgoingsAdapter.OutgoingViewHolder> {

    private List<Outgoing> mOutgoings;
    private String[] mCategories;

    public OutgoingsAdapter() {
        queryItems();
    }

    private void queryItems() {
        mOutgoings = Outgoing.listAll(Outgoing.class);
    }

    @NonNull
    @Override
    public OutgoingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.outgoing, viewGroup, false);

        mCategories = viewGroup.getResources().getStringArray(R.array.categories);

        return new OutgoingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutgoingViewHolder viewHolder, int pos) {
        final Outgoing outgoing = mOutgoings.get(pos);

        viewHolder.tvCategory.setText(mCategories[outgoing.getCategory()]);
        viewHolder.tvName.setText(outgoing.getName());
        viewHolder.tvValue.setText(String.format(Locale.getDefault(), "%d Ft", outgoing.getValue()));

        viewHolder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                outgoing.delete();

                reload();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOutgoings.size();
    }

    public void reload() {
        queryItems();

        notifyDataSetChanged();
    }

    class OutgoingViewHolder extends RecyclerView.ViewHolder {
        private View mView;

        private TextView tvCategory, tvName, tvValue;

        OutgoingViewHolder(View view) {
            super(view);

            mView = view;

            tvCategory = view.findViewById(R.id.tvCategory);
            tvName = view.findViewById(R.id.tvName);
            tvValue = view.findViewById(R.id.tvValue);
        }

        public View getView() {
            return mView;
        }
    }
}
