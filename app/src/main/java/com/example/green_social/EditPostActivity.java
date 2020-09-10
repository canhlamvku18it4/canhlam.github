package com.example.green_social;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.green_social.Fragments.HomeFragment;
import com.example.green_social.Models.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditPostActivity extends AppCompatActivity {
    private int position =0,id=0;
    private EditText txtDesc;
    private Button btnSave;
    private ProgressDialog dialog;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_post );
        init();
    }

    private void init() {
        sharedPreferences= getApplicationContext().getSharedPreferences( "user", Context.MODE_PRIVATE );
        txtDesc = findViewById( R.id.txtDescEditPost );
        btnSave = findViewById( R.id.btnEditPost );
        dialog = new ProgressDialog( this );
        dialog.setCancelable( false );
        position = getIntent().getIntExtra( "position",0 );
        id = getIntent().getIntExtra( "postId",0 );
        txtDesc.setText( getIntent().getStringExtra( "text" ) );

        btnSave.setOnClickListener( v -> {
            if(!txtDesc.getText().toString().isEmpty()){
                savePost();
            }
        } );

    }

    private void savePost() {
        dialog.setMessage( "Saving" );
        dialog.show();
        StringRequest request = new StringRequest( Request.Method.POST,Constant.UPDATE_POST,response -> {
            try {
                JSONObject object = new JSONObject( response );
                if (object.getBoolean( "success" )){
                    //update postin recyclerview
                    Post post = HomeFragment.postArrayList.get( position );
                    post.setDesc( txtDesc.getText().toString() );
                    HomeFragment.postArrayList.set( position,post );
                    HomeFragment.recyclerView.getAdapter().notifyItemChanged(position);
                    HomeFragment.recyclerView.getAdapter().notifyDataSetChanged();
                    finish();


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } ,error -> {

        }){
            //add token to header

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token =  sharedPreferences.getString( "token" ,"");
                HashMap<String,String> map = new HashMap<>(  );
                map.put( "Authorization","Bearer"+token );
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>(  );
                map.put("id",id+"");
                map.put( "desc",txtDesc.getText().toString() );
                return map;

            }
        };

        RequestQueue queue = Volley.newRequestQueue( EditPostActivity.this );
        queue.add( request );

    }

    public void cancelEdit(View view){
        super.onBackPressed();
    }
}