package com.example.a15041867.merchandise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class DisplayItemInfoActivity extends AppCompatActivity {

    private String userId;
    private EditText etItemName, etPrice,etQuantity;
    private Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item_info);



    }

    @Override
    protected void onStart() {
        super.onStart();
        etItemName = (EditText)findViewById(R.id.editTextItemName);
        etPrice = (EditText)findViewById(R.id.editTextPrice);
        etQuantity = (EditText)findViewById(R.id.editTextQuantity);
        btnDelete = (Button)findViewById(R.id.deleteButton);
        btnUpdate = (Button)findViewById(R.id.updateButton);

        Intent intent = getIntent();
        userId = intent.getStringExtra("com.example.MAIN_MESSAGE");
        HttpRequest request= new HttpRequest("http://10.0.2.2/C302_P07/getItemById.php?Id=" + userId);
        request.setMethod("GET");
        request.execute();

        try{
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields

            etItemName.setText(jsonObj.get("item_name").toString());
            etPrice.setText(jsonObj.get("quantity").toString());
            etQuantity.setText(jsonObj.get("price").toString());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRecordButtonClicked(View view){

        etItemName = (EditText)findViewById(R.id.editTextItemName);
        etPrice = (EditText)findViewById(R.id.editTextPrice);
        etQuantity = (EditText)findViewById(R.id.editTextQuantity);

        //TODO 03: Send the HttpRequest to updateContact.php
        HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/updateItemById.php");
        request.setMethod("POST");
        request.addData("id",userId);
        request.addData("item_name",etItemName.getText().toString());
        request.addData("quantity",etQuantity.getText().toString());
        request.addData("price",etPrice.getText().toString());
        request.execute();




        try{
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecordButtonClicked(View view){
        //TODO 04: Send HttpRequest to removeContact.php
        HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/deleteItem.php");
        request.setMethod("POST");
        request.addData("Id",userId);
        request.execute();



        try{


            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
