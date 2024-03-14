package com.example.drushim_alpha;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref
{
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance("https://drushim-alpha-default-rtdb.europe-west1.firebasedatabase.app/");

    public static DatabaseReference refUsers = FBDB.getReference("Users");
    public static DatabaseReference refPosts = FBDB.getReference("Posts");
}
