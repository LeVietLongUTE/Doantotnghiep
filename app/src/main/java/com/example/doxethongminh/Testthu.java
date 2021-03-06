package com.example.doxethongminh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doxethongminh.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Testthu extends AppCompatActivity {
        TextView txtusername,txtemail,welcom;
        Button btndangxuat;
        FirebaseUser user;
        DatabaseReference reference;
        String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testthu);

        btndangxuat=findViewById(R.id.btndangxuat);
        txtusername=findViewById(R.id.username);
        txtemail=findViewById(R.id.email);
        welcom=findViewById(R.id.welcom);
        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        UserID=user.getUid();
        reference.child(UserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile=snapshot.getValue(User.class);
                if (userprofile!=null){
                    String username=userprofile.username;
                    String email=userprofile.email;
                    welcom.setText("Welcom, "+username+"!");
                    txtusername.setText(username);
                    txtemail.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Testthu.this,login.class));
            }
        });

    }
}