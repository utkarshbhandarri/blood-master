package com.anchal.bloodcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView type, name, email, idNumber, phoneNumber, bloodGroup;
    private CircleImageView profileImage;
    private Button backButton;

    private TextView cityView, stateView;


    private String curr_city, curr_state;


    private String profileImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("My Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        type = findViewById(R.id.type);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        idNumber = findViewById(R.id.idNumber);
        phoneNumber = findViewById(R.id.phoneNumber);
        bloodGroup = findViewById(R.id.bloodGroup);
        profileImage = findViewById(R.id.profileImage);
        backButton = findViewById(R.id.backButton);

        cityView = findViewById(R.id.city);
        stateView = findViewById(R.id.state);




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    type.setText(snapshot.child("type").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    idNumber.setText(snapshot.child("idnumber").getValue().toString());
                    phoneNumber.setText(snapshot.child("phonenumber").getValue().toString());
                    bloodGroup.setText(snapshot.child("bloodgroup").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());



                    curr_city = "City : " + snapshot.child("city").getValue().toString();
                    cityView.setText(curr_city);

                    curr_state = "State : " + snapshot.child("state").getValue().toString();
                    stateView.setText(curr_state);



//                   profileImageUrl = snapshot.child("profilepictureurl").getValue().toString();

//                   if(!profileImage.equals("nourl")) {
//                       Glide.with(getApplicationContext()).load(profileImageUrl).into(profileImage);
//                   }


                    if(snapshot.hasChild("profilepictureurl"))
                    {
                        Glide.with(getApplicationContext()).load(snapshot.child("profilepictureurl")).into(profileImage);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}