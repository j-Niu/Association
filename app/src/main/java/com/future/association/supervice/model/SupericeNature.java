package com.future.association.supervice.model;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.future.association.common.utils.GsonUtils;

import org.json.JSONException;

public class SupericeNature extends BaseBean implements IPickerViewData {
    public static final Creator<SupericeNature> CREATOR = new Creator<>(SupericeNature.class);

    private String id;
    private String name;
    @Override
    public void parseInfo(String content) throws JSONException {
        list = GsonUtils.jsonToList(content,SupericeNature.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
