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
 * Created on 30/12/14 3:11 PM
 */
package com.odoo.config;

import com.odoo.addons.calendar.CalendarDashboard;
import com.odoo.addons.crm.CRMLeads;
import com.odoo.addons.customers.Customers;
import com.odoo.addons.phonecall.PhoneCalls;
import com.odoo.addons.sale.Sales;
import com.odoo.core.support.addons.AddonsHelper;
import com.odoo.core.support.addons.OAddon;

public class Addons extends AddonsHelper {

    /**
     * Declare your required module here
     * NOTE: For maintain sequence use object name in asc order.
     * Ex.:
     * OAddon partners = new OAddon(Partners.class).setDefault();
     */
    OAddon a_agenda = new OAddon(CalendarDashboard.class).setDefault();
    OAddon b_partners = new OAddon(Customers.class);
    OAddon c_crm_leads = new OAddon(CRMLeads.class);
    OAddon e_sale = new OAddon(Sales.class);
    OAddon f_phone_calls = new OAddon(PhoneCalls.class);
}
