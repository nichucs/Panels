package jp.satorufujiwara.binder;

import android.view.View;

/**
 * Created by nizamcs on 12/12/15.
 */
public interface BinderViewItemClickListener<M> {
    void onItemClicked(Object item, View view, int position, int section);
}
