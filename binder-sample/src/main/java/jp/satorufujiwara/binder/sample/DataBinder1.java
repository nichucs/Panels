package jp.satorufujiwara.binder.sample;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.satorufujiwara.binder.BinderViewItemClickListener;
import jp.satorufujiwara.binder.recycler.RecyclerBinder;

public class DataBinder1 extends RecyclerBinder<BinderSampleViewType> {

    private final int section;
    private String text;
    private final BinderViewItemClickListener itemClickListener;
    private final BinderViewItemClickListener itemPlayListener;

    public DataBinder1(Activity activity, int section, @NonNull BinderViewItemClickListener itemClickListener, BinderViewItemClickListener itemPlayListener) {
        super(activity, BinderSampleViewType.VIEW_TYPE_1);
        this.section = section;
        switch (section) {
            case 1 :
                this.text = "in section1";
                break;
            case 2 :
                this.text = "in section2";
                break;
            case 3 :
                this.text = "in section3";
                break;
        }
        this.itemClickListener = itemClickListener;
        this.itemPlayListener = itemPlayListener;
    }

    @Override
    public int layoutResId() {
        return R.layout.binder_data_1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.textSection.setText(text);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(null, v, position,section);
            }
        });
        holder.textSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPlayListener.onItemClicked(null, holder.textSection, position,section);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.text_section)
        TextView textSection;
        @InjectView(R.id.image)
        ImageView poster;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
