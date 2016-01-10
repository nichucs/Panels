package jp.satorufujiwara.binder.sample;

import jp.satorufujiwara.binder.ViewType;

public enum BinderSampleViewType implements ViewType {

    VIEW_TYPE_1,
    VIEW_TYPE_2,
    VIEW_TYPE_3,
    VIEW_TYPE_4,
    VIEW_TYPE_5,
    VIEW_TYPE_6;

    @Override
    public int viewType() {
        return ordinal();
    }

}
