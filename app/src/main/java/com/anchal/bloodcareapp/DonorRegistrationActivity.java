package com.anchal.bloodcareapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DonorRegistrationActivity extends AppCompatActivity
{
    private TextView backButton;

    private CircleImageView profile_image;

    private TextInputEditText registerFullName,registerIdNumber,
            registerPhoneNumber, registerEmail, registerPassword;



    private TextInputEditText registerCity, registerState;

    private Spinner bloodGroupsSpinner;
    private Spinner genderSpinner, hypertensionSpinner, diabetesSpinner, anemiaSpinner, anyOtherSpinner;

    private Spinner showPhoneSpinner;

    private TextInputEditText registerAge, registerHeight, registerWeight;






    private Button registerButton;

    private Uri resultUri;

    private ProgressDialog loader;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);


        profile_image = findViewById(R.id.profile_image);
        registerFullName = findViewById(R.id.registerFullName);
        registerIdNumber = findViewById(R.id.registerIdNumber);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        bloodGroupsSpinner = findViewById(R.id.bloodGroupsSpinner);
        registerButton = findViewById(R.id.registerButton);
        loader = new ProgressDialog(this);




        registerCity = findViewById(R.id.registerCity);
        registerState = findViewById(R.id.registerState);
        genderSpinner = findViewById(R.id.genderSpinner);
        hypertensionSpinner = findViewById(R.id.hypertensionSpinner);
        diabetesSpinner = findViewById(R.id.diabetesSpinner);
        anemiaSpinner = findViewById(R.id.anemiaSpinner);
        anyOtherSpinner = findViewById(R.id.anyOtherSpinner);

        registerAge = findViewById(R.id.registerAge);
        registerHeight = findViewById(R.id.registerHeight);
        registerWeight = findViewById(R.id.registerWeight);

        showPhoneSpinner = findViewById(R.id.showPhoneSpinner);



        mAuth = FirebaseAuth.getInstance();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonorRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        registerPhoneNumber.setText(OTPActivity.getPhoneNo().toString());
        registerPhoneNumber.setEnabled(false);



        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = registerEmail.getText().toString().trim();
                final  String password = registerPassword.getText().toString().trim();
                final String fullName = registerFullName.getText().toString().trim();
                final String idNumber = registerIdNumber.getText().toString().trim();
                final String phoneNumber = registerPhoneNumber.getText().toString().trim();
                final String bloodGroup = bloodGroupsSpinner.getSelectedItem().toString();



                final String city = registerCity.getText().toString().trim();
                final String state = registerState.getText().toString().trim();

                final String stringAge = registerAge.getText().toString().trim();
                final int age = Integer.parseInt(registerAge.getText().toString());

                final String stringHeight = registerHeight.getText().toString().trim();
                final int height = Integer.parseInt(registerHeight.getText().toString());

                final String stringWeight = registerWeight.getText().toString().trim();
                final int weight = Integer.parseInt(registerWeight.getText().toString());

                final String gender = genderSpinner.getSelectedItem().toString();
                final String hypertension = hypertensionSpinner.getSelectedItem().toString();
                final String diabetes = diabetesSpinner.getSelectedItem().toString();
                final String anemia = anemiaSpinner.getSelectedItem().toString();
                final String anyOther = anyOtherSpinner.getSelectedItem().toString();



                final String showPhone = showPhoneSpinner.getSelectedItem().toString();




                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email is required!");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    registerPassword.setError("Password is required!");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registerFullName.setError("Full name is required is required!");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)){
                    registerIdNumber.setError("Id Number is required!");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    registerPhoneNumber.setError("Phone Number is required!");
                    return;
                }
                if (bloodGroup.equals("Select your blood group")){
                    Toast.makeText(DonorRegistrationActivity.this, "Select Blood group", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(city))
                {
                    registerCity.setError("City is required");
                    return;
                }
                if(TextUtils.isEmpty(state))
                {
                    registerCity.setError("State is required");
                    return;
                }
                if(TextUtils.isEmpty(stringAge))
                {
                    registerAge.setError("Age is required");
                    return;
                }
                if(TextUtils.isEmpty(stringHeight))
                {
                    registerHeight.setError("Height is required");
                    return;
                }
                if(TextUtils.isEmpty(stringWeight))
                {
                    registerWeight.setError("weight is required");
                    return;
                }
                if(gender.equals("Select your Gender"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(hypertension.equals("Do you have Hypertension?"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Don you have hypertension?", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(diabetes.equals("Do you have Diabetes?"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Please Select Valid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(anemia.equals("Do you have Anemia?"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Please Select Valid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(anyOther.equals("Do you have any other disease of concern?"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Please Select Valid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(showPhone.equals("Do you want to show your Phone Number?"))
                {
                    Toast.makeText(DonorRegistrationActivity.this, "Please Select Valid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(DonorRegistrationActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String currentUserId = mAuth.getCurrentUser().getUid();

                                userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                        .child("users").child(currentUserId);

                                HashMap userInfo = new HashMap();

                                userInfo.put("id", currentUserId);

                                userInfo.put("name", fullName);
                                userInfo.put("email", email);
                                userInfo.put("idnumber", idNumber);
                                userInfo.put("phonenumber", phoneNumber);
                                userInfo.put("bloodgroup", bloodGroup);
                                userInfo.put("type", "donor");
                                userInfo.put("search", "donor"+bloodGroup);

                                userInfo.put("city", city);
                                userInfo.put("state", state);
                                userInfo.put("age", age);
                                userInfo.put("weight", weight);
                                userInfo.put("height", height);
                                userInfo.put("gender", gender);
                                userInfo.put("hypertension", hypertension);
                                userInfo.put("diabetes", diabetes);
                                userInfo.put("anemia", anemia);
                                userInfo.put("anyOther", anyOther);

                                userInfo.put("showPhone", showPhone);

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(DonorRegistrationActivity.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(DonorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }

                                        finish();
                                        //loader.dismiss();
                                    }
                                });

                                if (resultUri !=null){
                                    final StorageReference filePath = FirebaseStorage.getInstance().getReference()
                                            .child("profile images").child(currentUserId);

                                    Bitmap bitmap = null;

                                    try {
                                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }

                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                                    byte[] data  = byteArrayOutputStream.toByteArray();
                                    UploadTask uploadTask = filePath.putBytes(data);

                                    uploadTask.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DonorRegistrationActivity.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            if (taskSnapshot.getMetadata() !=null && taskSnapshot.getMetadata().getReference() !=null){
                                                Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {

                                                        String imageUrl = uri.toString();

                                                        Map newImageMap = new HashMap();
                                                        newImageMap.put("profilepictureurl", imageUrl);

                                                        userDatabaseRef.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                                            @Override
                                                            public void onComplete(@NonNull Task task) {
                                                                if (task.isSuccessful()){
                                                                    Toast.makeText(DonorRegistrationActivity.this, "Image url added to database successfully", Toast.LENGTH_SHORT).show();
                                                                }else {
                                                                    Toast.makeText(DonorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                                        finish();
                                                    }
                                                });
                                            }

                                        }
                                    });

                                    Intent intent = new Intent(DonorRegistrationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    loader.dismiss();
                                }

//                                else
//                                {
//                                    Map newImageMap = new HashMap();
//                                    newImageMap.put("profilepictureurl", "nourl");
//
//
//                                    userDatabaseRef.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
//                                        @Override
//                                        public void onComplete(@NonNull Task task) {
//                                            if (task.isSuccessful()){
//                                                Toast.makeText(DonorRegistrationActivity.this, "Image url added to database successfully", Toast.LENGTH_SHORT).show();
//                                            }else {
//                                                Toast.makeText(DonorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
//
//                                    finish();
//                                }
                            }

                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode == RESULT_OK && data !=null){
            resultUri = data.getData();
            profile_image.setImageURI(resultUri);
        }
    }
}