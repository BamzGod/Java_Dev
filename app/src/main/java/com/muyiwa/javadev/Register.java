package com.muyiwa.javadev;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Register extends AppCompatActivity {

    ImageView im;
    EditText eBvn, eFname, eLname, eMname, ePhone, eCity, eTown, eAddress;
    AutoCompleteTextView genderDd, stateDd, maritalDd, branchDd, accountDd;


    Button datedb, createAcc;
    TextView tDob;
    String sBvn, sFname, sLname, sMname, sPhone, sCity, sGender, sState, sMarital, sBranch, sAccount, sDob, sTown, sAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createAcc = findViewById(R.id.btnCreateAccount);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(eBvn.getText())) {
                    Toast.makeText(Register.this, "BVN is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(eFname.getText())) {
                    Toast.makeText(Register.this, "First Name is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(eLname.getText())) {
                    Toast.makeText(Register.this, "Last Name is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(eMname.getText())) {
                    Toast.makeText(Register.this, "Middle Name is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(ePhone.getText())) {
                    Toast.makeText(Register.this, "Phone Number is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(eCity.getText())) {
                    Toast.makeText(Register.this, "City is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(datedb.getText())) {
                    Toast.makeText(Register.this, "Date of birth is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(eMname.getText())) {
                    Toast.makeText(Register.this, "Middle Name is mandatory", Toast.LENGTH_SHORT).show();
                } else {

                    sBvn = eBvn.getText().toString();
                    sFname = eFname.getText().toString();
                    sLname = eLname.getText().toString();
                    sMname = eMname.getText().toString();
                    sPhone = ePhone.getText().toString();
                    sAddress = eAddress.getText().toString();
                    sTown = eTown.getText().toString();
                    sCity = eCity.getText().toString();
                    sGender = genderDd.getText().toString();
                    sMarital = maritalDd.getText().toString();
                    sState = stateDd.getText().toString();
                    sBranch = branchDd.getText().toString();
                    sAccount = accountDd.getText().toString();
                    sDob = tDob.getText().toString();
                    postData(sBvn, sFname, sLname, sMname, sPhone, sCity, sGender, sState, sMarital, sBranch, sAccount, sDob, sTown, sAddress);

                }
            }
        });


        eBvn = findViewById(R.id.EdBvn);
        ePhone = findViewById(R.id.EdphoneNum);
        eFname = findViewById(R.id.Edfirstname);
        eLname = findViewById(R.id.Edlastname);
        eMname = findViewById(R.id.EdmiddleName);
        eAddress = findViewById(R.id.Edaddress);
        eTown = findViewById(R.id.Edtown);
        eCity = findViewById(R.id.Edcity);

        datedb = findViewById(R.id.btnDob);
        tDob = findViewById(R.id.tvDob);


        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT A DATE");

        final MaterialDatePicker materialDatePicker = builder.build();
        datedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                tDob.setText(materialDatePicker.getHeaderText());
            }
        });


        genderDd = findViewById(R.id.GenderAutoCompleteTextview3);
        String[] gender = new String[]{"Male", "Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(Register.this, R.layout.dropdown, gender);
        genderDd.setAdapter(genderAdapter);


        maritalDd = findViewById(R.id.MaritalAutoCompleteTextview3);
        String[] marital = new String[]{"Single", "Married", "Divorced"};
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<>(Register.this, R.layout.dropdown, marital);
        maritalDd.setAdapter(maritalAdapter);


        accountDd = findViewById(R.id.AccountTypeAutoCompleteTextview3);
        String[] account = new String[]{"Savings", "Current", "Fixed"};
        ArrayAdapter<String> accountAdapter = new ArrayAdapter<>(Register.this, R.layout.dropdown, account);
        accountDd.setAdapter(accountAdapter);


        stateDd = findViewById(R.id.stateAutoCompleteTextview3);
        String[] state = new String[]{"Abia", "Abia", "Adamawa", "Akwa Ibom", "Anambra", "Bauchi", "Bayelsa", "Benue", "Borno", "Cross River", " Delta", "Ebonyi", "Edo", "Ekiti", "Enugu", "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina", "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun", "Ondo", "Osun", "Oyo", "Plateau", "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara", "FCT"};
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(Register.this, R.layout.dropdown, state);
        stateDd.setAdapter(stateAdapter);


        branchDd = findViewById(R.id.BranchAutoCompleteTextview3);
        String[] branch = new String[]{"Mokola", "Dugbe", "Aleniboro", "Onipan", "Lasisi", "Ajibode", "Agege", "Shomolu", "Akinyemi", "Shonibare", "Dakija", "Ilesanmi", "Ayeni"};
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(Register.this, R.layout.dropdown, branch);
        branchDd.setAdapter(branchAdapter);


        im = findViewById(R.id.backarrow);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void postData(String pBvn, String pFname, String pLname, String pMname, String pPhone, String pCity, String pGender, String pState, String pMarital, String pBranch, String pAccount, String pDob, String pTown, String pAddress) {
//        destination
        String url = "https://hgt.ng/api/user/account/customer.php";
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing your data...");
        progressDialog.show();

        JSONObject object = new JSONObject();
        try {
            object.put("firstName", pFname);
            object.put("lastName", pLname);
            object.put("middleName", pMname);
            object.put("birthDate", pDob);
            object.put("address", pAddress);
            object.put("town", pTown);
            object.put("city", pCity);
            object.put("phone_number", pPhone);
            object.put("gender", pGender);
            object.put("branch", pBranch);
            object.put("maritalStatus", pMarital);
            object.put("bvn", pBvn);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Payload:" + object);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String ResponseCode = "";
                String ResponseMessage = "";
                try {
                    ResponseCode = jsonObject.getString("ResponseCode");
                    ResponseMessage = jsonObject.getString("ResponseMessage");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                if (ResponseCode.equals("00")) {
                    progressDialog.hide();
                    Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.hide();
                    Toast.makeText(Register.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.hide();
                Toast.makeText(Register.this, "registration failed", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-type", "application/json");

                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}