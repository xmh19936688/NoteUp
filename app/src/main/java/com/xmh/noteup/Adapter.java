package com.xmh.noteup;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by void on 2017/6/26 026.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<Pair<String, Date>> mData = new ArrayList<>();

    public void setData(List<Pair<String, Date>> list) {
        mData.clear();
        if (list != null && !list.isEmpty()) {
            for (Pair p : list) {
                mData.add(p);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        Pair<String, Date> p;
        TextView tvName;
        TextView tvDate;

        public Holder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvDate = (TextView) itemView.findViewById(R.id.date);
        }

        public void bind(Pair<String, Date> p) {
            this.p = p;
            tvName.setText(p.first);
            tvDate.setText(new SimpleDateFormat("yyyy年MM月dd日").format(p.second));
        }
    }
}
