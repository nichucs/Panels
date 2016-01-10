package jp.satorufujiwara.binder.sample;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.satorufujiwara.binder.BinderViewItemClickListener;
import jp.satorufujiwara.binder.Section;
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

public class BinderSampleFragment extends Fragment {

    private Activity mActivity;
    DataBinder2 customBinder;

    public static BinderSampleFragment newInstance() {
        return new BinderSampleFragment();
    }

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    private final RecyclerBinderAdapter<BinderSampleSection, BinderSampleViewType> adapter
            = new RecyclerBinderAdapter<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_binder_sample, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
//        adapter.disableRecycle(true);
        recyclerView.setAdapter(adapter);

        BinderViewItemClickListener movieClick = new BinderViewItemClickListener() {
            @Override
            public void onItemClicked(Object item, View view, int position, int section) {
                Toast.makeText(mActivity, "Clicked for detail at "+position, Toast.LENGTH_LONG).show();
            }
        };

        BinderViewItemClickListener playClick = new BinderViewItemClickListener() {
            @Override
            public void onItemClicked(Object item, final View view, final int position, final int section) {
                Toast.makeText(mActivity, "Clicked for play at "+position, Toast.LENGTH_LONG).show();
                new WaitThread(){
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        switch (section) {
                            case 1 :
                                adapter.replace(BinderSampleSection.SECTION_1,customBinder, position);
                                break;
                            case 2:
                                adapter.replace(BinderSampleSection.SECTION_2,customBinder, position);
                                break;
                            case 3:
                                adapter.replace(BinderSampleSection.SECTION_3,customBinder, position);
                                break;
                        }
                    }
                }.execute(3000);
            }
        };

        customBinder = new DataBinder2(mActivity,"Clicked");

        adapter.add(BinderSampleSection.SECTION_1, new DataBinder1(getActivity(), 1, movieClick, playClick));
        adapter.add(BinderSampleSection.SECTION_1, new DataBinder2(getActivity(), "in Section1"));
        adapter.add(BinderSampleSection.SECTION_1, new DataBinder3(getActivity(), "in Section1"));

        adapter.add(BinderSampleSection.SECTION_3, new DataBinder2(getActivity(), "in Section3"));
        adapter.add(BinderSampleSection.SECTION_3, new DataBinder1(getActivity(), 3, movieClick, playClick));

        adapter.add(BinderSampleSection.SECTION_2, new DataBinder3(getActivity(), "in Section2"));
        adapter.add(BinderSampleSection.SECTION_2, new DataBinder5(getActivity(), "Second Section", BinderSampleViewType.VIEW_TYPE_5));
        adapter.add(BinderSampleSection.SECTION_2, new DataBinder1(getActivity(), 2, movieClick, playClick));

        adapter.add(BinderSampleSection.SECTION_4, new DataBinder4(getActivity(),"Fourth section"));
        adapter.add(BinderSampleSection.SECTION_4, new DataBinder2(getActivity(), "in Section4"));

        adapter.add(BinderSampleSection.SECTION_3, new DataBinder5(getActivity(), "Third Section", BinderSampleViewType.VIEW_TYPE_6));
    }

    enum BinderSampleSection implements Section {

        SECTION_1,
        SECTION_2,
        SECTION_3,
        SECTION_4,
        SECTION_5,
        SECTION_6;


        @Override
        public int position() {
            return ordinal();
        }
    }
}
