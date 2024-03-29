/*
 *
 *    Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.citrus.sample;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by salil on 29/5/15.
 */
public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public static enum PaymentType {
        LOAD_MONEY, CITRUS_CASH, PG_PAYMENT, DYNAMIC_PRICING;
    }

    public enum DPRequestType {
        SEARCH_AND_APPLY, CALCULATE_PRICING, VALIDATE_RULE;
    }
}
