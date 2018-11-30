package com.to_do_win.mixme_v2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;
import com.to_do_win.mixme_v2.utilities.LogToggle;
import com.to_do_win.mixme_v2.utilities.UserManager;

public class NewIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    String userName;
    TextView greeting;
    EditText nameET;
    Button logBtn, cancelBtn, submitBtn;
    RadioGroup categoryRG;
    RadioButton selectedRB;
    String packageName = "com.to_do_win.mixme_v2";
    Controller controller;
    LogToggle logToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ingredient);
        logToggle = new LogToggle();

        controller = Controller.getInstance();

        userName = UserManager.getUserName();

        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText(userName);

        logBtn = (Button) findViewById(R.id.logBtn);
        logBtn.setText("Log Out");
        logBtn.setOnClickListener(this);

        nameET = (EditText) findViewById(R.id.nameET);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        categoryRG = (RadioGroup) findViewById(R.id.categoryRG);

        // need to figure out this
        //spiritRB = (RadioButton) findViewById(R.id.spirit)

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {

            case R.id.logBtn:
                startActivity(logToggle.logToggle(logBtn,greeting,packageName));
                break;

            case R.id.cancelBtn:
                intent.setClassName(packageName,
                        packageName +".UI.SelectIngredientActivity");
                startActivity(intent);
                break;

            case R.id.submitBtn:
                // assure we don't already have ingredient
                String newIngName = nameET.getText().toString();

                if (controller.getIngredientID(newIngName) >= 0) {
                    Toast.makeText(this, "Ingredient already exist. Either go back to select ingredient or check spelling",
                            Toast.LENGTH_LONG ).show();


                } else {
                    intent.putExtra("is new ingredient", true);
                    intent.putExtra("ingredient_name", newIngName);


                    // this probably want to change to stringExtra instead???
                    int selectedID = categoryRG.getCheckedRadioButtonId();
                    selectedRB = (RadioButton) findViewById(selectedID);
                    String catName = selectedRB.getText().toString();
                    Log.i("Debug", catName);

                    intent.putExtra("ingredient category", catName);
                    categoryRG.clearCheck();
                    intent.setClassName(packageName,
                            packageName +".UI.IngredientVolumeActivity");
                    startActivity(intent);
                }
                break;
        }
    }
}
