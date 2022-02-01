package com.danaekikue.login_demo.Login.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danaekikue.login_demo.Login.Magazines.MagazinesActivity;
import com.danaekikue.login_demo.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    //API Base URL
    public static final String URL = "https://3nt-demo-backend.azurewebsites.net/Access/Login";

    private EditText userID, password;
    private TextView success_text,view_pass, info_m, bar_tittle;
    private ImageView userInfo, passInfo;
    private Button login_btn, back_to_login_btn,back_to_login_btn_empty;

    private int clickNum;
    private String info_type;

    private Gson gson;
    private RequestQueue queue;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bar_tittle = findViewById(R.id.top_bar_tittle);
        bar_tittle.setText(R.string.login_title);

        userID = findViewById(R.id.login_id_value);
        password = findViewById(R.id.login_password_value);

        login_btn = findViewById(R.id.login_button);
        back_to_login_btn = findViewById(R.id.back_to_login_btn);
        back_to_login_btn_empty = findViewById(R.id.back_to_login_btn_empty);

        userInfo = findViewById(R.id.info_icon_id);
        passInfo = findViewById(R.id.info_icon_pass);
        view_pass = findViewById(R.id.view_password);

        dialog = new Dialog(this);

        // Login
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUserID = userID.getText().toString();
                String inputPassword = password.getText().toString();

                if (inputUserID.isEmpty() || inputPassword.isEmpty()){
                    showErrorMessageEmptyInput();
                }else if(!userIDValidity(inputUserID)){
                    //!userIDValidity(inputUserID)
                    showErrorMessageWrongCredentials();
                }else{
                    login(inputUserID, inputPassword);
                }
            }
        });

        //When the info icon of the user ID is clicked, show an alert dialog
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info_type = "USER_ID";
                showInfo(info_type);
            }
        });

        //When the info icon of the password is clicked, show an alert dialog
        passInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info_type = "PASSWORD";
                showInfo(info_type);
            }
        });

        //Toggle password visibility as well as indication for which state you are in
        view_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNum++;
                passwordVisibility(clickNum);
            }
        });
    }

    //Method for API connection to pass the user's credentials and check their validity
    private void login(String inputUserID, String inputPassword){

        queue = Volley.newRequestQueue(this);
        gson = new Gson();

        // Request a string response from the provided URL.
        StringRequest stringRequest  = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        User user = gson.fromJson(response,User.class);
                        String access_token = user.getAccessToken();
                        Intent intent = new Intent( LoginActivity.this ,MagazinesActivity.class);
                        intent.putExtra("access_token", access_token);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APPLOG", error.toString());
                showErrorMessageWrongCredentials();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("UserName", inputUserID);
                params.put("Password", inputPassword);
                return params;
            }
        };

        queue.add(stringRequest);
    }

    private static boolean userIDValidity(String userID){
        Pattern pattern;
        Matcher matcher;
        String USERID_PATTERN = "^[A-Z]{2}[0-9]{4}";
        pattern = Pattern.compile(USERID_PATTERN);
        matcher = pattern.matcher(userID);
        return matcher.matches();
    }

    private boolean passwordValidity(String password){

        Boolean isValid=false, legnth= false, upper= false, lower= false, special_char= false, numbers = false;

        if (password.length() <= 8){
            legnth = true;
            Log.d("legnth", legnth.toString());
        }else

        if (password.matches("[A-Z]{2,}")){
            upper  = true;
            Log.d("upper", upper.toString());
        }

        if (password.matches("[a-z]{3,}")){
            lower  = true;
            Log.d("lower", lower.toString());
        }

        if (password.matches("[0-9]{2,}")){
            special_char  = true;
            Log.d("special_char", special_char.toString());
        }

        if (password.matches("[@#$%^&+=]+")){
            numbers  = true;
            Log.d("numbers", numbers.toString());
        }

        if (legnth && upper && lower && special_char && numbers ){
            isValid =true;
            Log.d("isValid", isValid.toString());
        }
        Log.d("isValid", isValid.toString());
        return isValid;
    }

    private void showErrorMessageWrongCredentials(){
        dialog.setContentView(R.layout.activity_error_message);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button back_button = dialog.findViewById(R.id.back_to_login_btn);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showErrorMessageEmptyInput(){
        dialog.setContentView(R.layout.activity_empty_input_error_message);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button back_button = dialog.findViewById(R.id.back_to_login_btn_empty);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showInfo(String info_type){

        dialog.setContentView(R.layout.activity_pop_up_info);
        info_m = dialog.findViewById(R.id.info_message);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (info_type.equals("USER_ID")){
            info_m.setText(R.string.user_format_info);
        }else {
            info_m.setText(R.string.pass_format_info);
        }
        dialog.show();
    }

    private void passwordVisibility(int clickNum){
        if (clickNum % 2 == 0 ){
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            view_pass.setText(R.string.show_password);
        }else {
            password.setInputType(1);
            view_pass.setText(R.string.hide_password);
        }
    }

}