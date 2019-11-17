package com.hospitalapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hospitalapp.models.Area;
import com.hospitalapp.models.Hospital;
import com.hospitalapp.models.Interception;
import com.hospitalapp.models.Specialization;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticMembers {

    public static final String domain = "https://insightclosely.com/happy/public/api/";
    public static final String CAT = "categories";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_ID = "category_id";
    public static final String USERS = "users";
    public static final String USER = "user";
    public static final String FAV = "fav";
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String DATE_FORMAT_VIEW = "yyyy-M-dd hh:mm";
    public static final String SIGN_UP = "sign_up";
    public static final String PIN_CODE = "pincode";
    public static final String PIN_CODE_TOKEN = "pincodetoken";
    public static final String PRODUCT = "product";
    public static final String NAME = "name";
    public static final String SEARCH = "search";
    public static final String SUB_CATEGORY = "subcategory";
    public static final String ID = "id";
    public static final String WISHLIST = "wishlist";
    public static final String WISHLIST_ACTION = "wishlist/action/{id}";
    public static final String SORT = "sort";
    public static final String AMOUNT = "amount";
    public static final String CART = "cart";
    public static final String EDIT_CART = "cart/store";
    public static final String QUANTITY = "quantity";
    public static final String PRODUCT_ID = "product_id";
    public static final String DELETE_CART = "cart/delete/{id}";
    public static final String VIDEO = "video";
    public static final String IMAGE = "image";
    public static final String STOP = "stop";
    public static final String ACTION = "action";
    public static final String SLIDER = "slider";
    public static final String EDIT_NAME = "profile/edit";
    public static final String CONTACT = "contact";
    public static final String MESSAGE = "message";
    public static final String GIFT = "gift";
    public static final String CODE = "code";
    public static final String MAX_AMOUNT = "max";
    public static final String UNIT_SIZE = "unitsize";
    public static final String COLOR = "color";
    public static final String SUB_CATEGORY_ID = "subcategory_id";
    public static final String OFFERS = "offer";
    public static final String DISCOUNT = "discount";
    public static final String GIFT_GET = "gifts/all";
    public static final String GIFT_COVER = "gifts/cover";
    public static final String GIFT_ADDITION = "gifts/additional";
    public static final String GIFT_MESSAGE = "gifts/message";
    public static final String GIFT_COVERS_ID = "gifts_covers";
    public static final String GIFT_MESSAGE_ID = "gift_messegs";
    public static final String GIFT_MESSAGE_FROM = "gift_messegs_from";
    public static final String GIFT_MESSAGE_TO = "gift_messegs_to";
    public static final String GIFT_MESSAGE_TEXT = "gift_messegs_msg";
    public static final String GIFT_ADDITION_IDS = "gifts_additionals[]";
    public static final String GIFT_QUANTITY_ADDITION = "quantity_additionals[]";
    public static final String GOV = "governmant";
    public static final String AREA = "area";
    public static final String BLOCK = "block";
    public static final String STREET = "street";
    public static final String AVENUE = "avenue";
    public static final String REMARK_ADDRESS = "remarkaddress";
    public static final String HOUSE_NO = "house_no";
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final int LOCATION_CODE = 5544;
    private static final String LANGUAGE = "language";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LAT = "latitude";
    public static final String LONG = "longitude";
    public static final String LON = "lon";
    public static final String LAT_ = "lat";
    public static final String ADDRESS = "address";
    public static final String TOKEN = "token";
    public static final String PAGE = "page";
    public static final String register = "register";
    public static final String getPinCode = "user/getPinCode";
    public static final String login = "login";
    public static final String verifyPinCode = "user/testpincode";
    public static final String reset = "user/password";


    ///////////////////DATA Static///////////////////////

    public static List<Hospital> getAllHospitals() {
        Area shoubra = new Area("Shoubra", 11);
        List<Hospital> list = new ArrayList<>();
        List<Specialization> specializations = new ArrayList<>(getAllSpec());

        specializations.remove(0);
        Hospital hospital = new Hospital("El Khazendara General Hospital",
                "Cairo",
                " El Khazendara General Hospital is a huge leading hospital is a huge leading hospital",
                30.10342045865799,
                31.319859412193864,
                shoubra,
                specializations);

        list.add(hospital);
        hospital = new Hospital("Sahel Teaching Hospital", "Cairo", "Sahel Teaching Hospital is a huge leading hospital", 30.085680424211166, 31.24983593309628, shoubra, null);
        list.add(hospital);
        shoubra = new Area("Maadi", 13);
        hospital = new Hospital("Kasr El Maadi Hospital - KMH", "Maadi", "Kasr El Maadi Hospital is a huge leading hospital", 29.990517012159287, 31.372307149068774, shoubra, null);
        list.add(hospital);

        specializations = new ArrayList<>(getAllSpec());
        specializations.remove(0);
        specializations.remove(0);
        specializations.remove(0);
        hospital = new Hospital("Spinnies El Maadi Hospital - KMH", "Maadi", "Spinnies El Maadi Hospital is a huge leading hospital", 29.9697417, 31.2877644, shoubra, specializations);
        list.add(hospital);

        specializations = new ArrayList<>(getAllSpec());

        specializations.remove(0);
        specializations.remove(specializations.size() - 1);

        shoubra = new Area("Misr algadida", 14);
        hospital = new Hospital("Cleopatra Hospital", "Misr algadida", "Cleopatra hospital is a huge leading hospital", 30.0930807, 31.3276036, shoubra, specializations);
        list.add(hospital);

        shoubra = new Area("Madinat Nasr", 15);
        hospital = new Hospital("Dar El Foad", "Madinat Nasr", "Dar El Foad hospital is a huge leading hospital", 30.0678353, 31.3426359, shoubra, null);
        list.add(hospital);
        return list;
    }

    public static List<Area> getAllAreas() {
        List<Area> list = new ArrayList<>();
        Area shoubra = new Area("All Areas", -1);
        list.add(shoubra);
        shoubra = new Area("Shoubra", 11);
        list.add(shoubra);
        shoubra = new Area("Maadi", 13);
        list.add(shoubra);
        shoubra = new Area("Misr algadida", 14);
        list.add(shoubra);
        shoubra = new Area("Madinat Nasr", 15);
        list.add(shoubra);
        return list;
    }

    public static List<Specialization> getAllSpec() {
        List<Specialization> list = new ArrayList<>();
        Specialization shoubra = new Specialization("All Specializations", -1);
        list.add(shoubra);
        shoubra = new Specialization("Brain", 11);
        list.add(shoubra);
        shoubra = new Specialization("Nose", 13);
        list.add(shoubra);
        shoubra = new Specialization("Heart", 14);
        list.add(shoubra);
        shoubra = new Specialization("Bones", 15);
        list.add(shoubra);
        return list;
    }

    public static List<Interception> getAllInterception() {
        List<Interception> list = new ArrayList<>();
        Interception interception = new Interception(new Pair<>("Panadol 1", "Panadol 2"), "Hello world! 1 2Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \n");
        list.add(interception);
        interception = new Interception(new Pair<>("Panadol 2", "Panadol 3"), "Hello world! 2 3 Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \n");
        list.add(interception);
        interception = new Interception(new Pair<>("Panadol 4", "Panadol 3"), "Hello world! 4 3 Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \n");
        list.add(interception);
        interception = new Interception(new Pair<>("Panadol 4", "Panadol 2"), "Hello world! 4 2 Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \n");
        list.add(interception);
        interception = new Interception(new Pair<>("Panadol 1", "Panadol 4"), "Hello world! 1 4 Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \nHello world! Hello world! Hello world! \n");
        list.add(interception);
        return list;
    }

    public static List<String> getAllDrugs() {
        List<String> list = new ArrayList<>();
        String s = "Panadol 1";
        list.add(s);
        s = "Panadol 2";
        list.add(s);
        s = "Panadol 3";
        list.add(s);
        s = "Panadol 4";
        list.add(s);
        return list;
    }

    /////////////////Dates converter/////////////////////
    public static String changeDateFromIsoToView(String dateFrom) {
        SimpleDateFormat sdf = new SimpleDateFormat(StaticMembers.ISO_DATE_FORMAT, Locale.US);
        SimpleDateFormat sdfTo = new SimpleDateFormat(StaticMembers.DATE_FORMAT_VIEW, Locale.getDefault());
        try {
            return sdfTo.format(sdf.parse(dateFrom));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFrom;
    }

    public static <T extends Activity> void startActivityOverAll(T activity, Class<?> destinationActivity) {
        Intent intent = new Intent(activity, destinationActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finishAffinity();
    }

    public static <T extends Activity> void startActivityOverAll(Intent intent, T activity) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finishAffinity();
    }

    public static <T extends View> void hideKeyboard(T view, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static <A extends Activity> void hideKeyboard(A activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    //////////////////////Password validation/////////////////////
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean CheckValidationPassword(TextInputEditText editText, final TextInputLayout textInputLayout, final String errorMessage, final String errorMessage2) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    textInputLayout.setError(errorMessage);
                    textInputLayout.setErrorEnabled(true);
                } else {
                    if (!isValidPassword(s.toString())) {

                        textInputLayout.setError(errorMessage2);
                        textInputLayout.setErrorEnabled(true);
                    } else {
                        textInputLayout.setErrorEnabled(false);
                    }
                }

            }
        });
        if (TextUtils.isEmpty(editText.getText())) {
            textInputLayout.setError(errorMessage);
            textInputLayout.setErrorEnabled(true);
            return false;
        } else {
            if (!isValidPassword(editText.getText().toString())) {
                textInputLayout.setError(errorMessage2);
                textInputLayout.setErrorEnabled(true);
                return false;
            } else {
                textInputLayout.setErrorEnabled(false);
                return true;
            }
        }
    }
    //////////////////////Visiblity with Animation/////////////////////

    public static <V extends View> void makeVisible(V layout) {
        layout.setVisibility(View.VISIBLE);
        layout.setAlpha(0.0f);
        layout.animate()
                .translationY(0)
                .alpha(1.0f)
                .setListener(null);
    }

    public static <V extends View> void makeGone(V layout) {
        layout.setVisibility(View.GONE);
    }
    //////////////////////Toasts/////////////////////

    private static Toast toast;

    public static void toastMessageShort(Context context, String messaage) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, messaage, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void toastMessageShort(Context context, int messaage) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, messaage, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void toastMessageShort(Context context, CharSequence messaage) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, messaage, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void toastMessageLong(Context context, int messaage) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, messaage, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void toastMessageLong(Context context, String messaage) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, messaage, Toast.LENGTH_LONG);
        toast.show();
    }

    public static boolean CheckTextInputEditText(TextInputEditText editText, final TextInputLayout textInputLayout, final String errorMessage) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    textInputLayout.setError(errorMessage);
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });
        if (TextUtils.isEmpty(editText.getText())) {
            textInputLayout.setError(errorMessage);
            textInputLayout.setErrorEnabled(true);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    public static String getLanguage(Context context) {
        return PrefManager.getInstance(context).getStringData(LANGUAGE);
    }

    public static void setLanguage(Context context, String langStr) {
        PrefManager.getInstance(context).setStringData(LANGUAGE, langStr);
    }

    public static void changeLocale(Context context, String langStr) {
        setLanguage(context, langStr);
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(langStr.toLowerCase()));
        res.updateConfiguration(conf, dm);
        Locale locale = context.getResources().getConfiguration().locale;
        Locale.setDefault(locale);
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd hh:mma", Locale.getDefault()).format(calendar.getTime());

    }


    public static String getDate(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());
    }
/*
    public static void openLogin(Context activity) {
        Intent intent = new Intent(activity, LogInActivity.class);
        intent.putExtra(StaticMembers.ACTION, true);
        activity.startActivity(intent);
    }

    public static void checkLoginRequired(ResponseBody errorBody, Context context) {
        try {
            ErrorLoginResponse errorLoginResponse = null;
            if (errorBody != null) {
                errorLoginResponse = new GsonBuilder().create().fromJson(errorBody.string(), ErrorLoginResponse.class);
                if (errorLoginResponse != null) {
                    if (errorLoginResponse.getError() != null && !errorLoginResponse.getError().isEmpty() && errorLoginResponse.getError().toLowerCase().contains("token")) {
                        PrefManager.getInstance(context).removeToken();
                        openLogin(context);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
