package jp.satorufujiwara.binder.sample;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.satorufujiwara.binder.recycler.RecyclerBinder;

public class DataBinder4 extends RecyclerBinder<BinderSampleViewType> {

    private final List<String> text;
    private Activity activity;

    public DataBinder4(Activity activity, String text) {
        super(activity, BinderSampleViewType.VIEW_TYPE_4);
        this.activity = activity;
        this.text = new ArrayList<>();
        for (int i=0; i<15 ; i++) this.text.add(text+i);
    }

    @Override
    public int layoutResId() {
        return R.layout.binder_data_4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (holder.panel.getAdapter() == null) {
            holder.panel.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            holder.panel.setAdapter(new PanelAdapter(activity, R.layout.binder_data_1, text));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.panel)
        RecyclerView panel;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
