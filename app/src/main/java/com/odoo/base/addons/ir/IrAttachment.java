/**
 * Odoo, Open Source Management Solution
 * Copyright (C) 2012-today Odoo SA (<http:www.odoo.com>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:www.gnu.org/licenses/>
 *
 * Created on 31/12/14 12:41 PM
 */
package com.odoo.base.addons.ir;

import android.content.Context;

import com.odoo.base.addons.res.ResCompany;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OInteger;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

import org.json.JSONException;
import org.json.JSONObject;

import odoo.ODomain;

public class IrAttachment extends OModel {
    public static final String TAG = IrAttachment.class.getSimpleName();

    OColumn name = new OColumn("Name", OVarchar.class);
    OColumn datas_fname = new OColumn("Data file name", OText.class);
    OColumn file_size = new OColumn("File Size", OInteger.class);
    OColumn res_model = new OColumn("Model", OVarchar.class).setSize(100);
    OColumn file_type = new OColumn("Content Type", OVarchar.class).setSize(100);
    OColumn company_id = new OColumn("Company", ResCompany.class,
            OColumn.RelationType.ManyToOne);
    OColumn res_id = new OColumn("Resource id", OInteger.class).setDefaultValue(0);
    OColumn scheme = new OColumn("File Scheme", OVarchar.class).setSize(100)
            .setLocalColumn();
    // Local Column
    OColumn file_uri = new OColumn("File URI", OVarchar.class).setSize(150)
            .setLocalColumn().setDefaultValue(false);
    OColumn type = new OColumn("Type", OText.class).setLocalColumn();

    public IrAttachment(Context context, OUser user) {
        super(context, "ir.attachment", user);
    }

    public boolean createAttachment(OValues value, String rel_model, int res_id) {
        OValues values = new OValues();
        values.put("name", value.get("name"));
        values.put("datas_fname", value.getString("name"));
        values.put("file_size", value.get("file_size"));
        values.put("file_type", value.get("file_type"));
        values.put("company_id", getUser().getCompany_id());
        values.put("res_id", res_id);
        values.put("res_model", rel_model);
        values.put("file_uri", value.getString("file_uri"));
        values.put("type", value.getString("file_type"));
        values.put("id", value.get("id"));
        insert(values);
        return true;
    }

    public static JSONObject valuesToData(OModel model, OValues value) {
        JSONObject data = new JSONObject();
        try {
            data.put("name", value.get("name"));
            data.put("db_datas", value.getString("datas"));
            data.put("datas_fname", value.get("name"));
            data.put("file_size", value.get("file_size"));
            data.put("res_model", false);
            data.put("res_id", false);
            data.put("file_type", value.get("file_type"));
            data.put("company_id", model.getUser().getCompany_id());
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDatasFromServer(Integer row_id) {
        try {
            ODomain domain = new ODomain();
            domain.add("id", "=", selectServerId(row_id));
            JSONObject fields = new JSONObject();
            fields.accumulate("fields", "datas");
            JSONObject result = getServerDataHelper().getOdoo().search_read(getModelName(), fields,
                    domain.get());
            if (result.getJSONArray("records").length() > 0) {
                JSONObject row = result.getJSONArray("records")
                        .getJSONObject(0);
                return row.getString("datas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
}
