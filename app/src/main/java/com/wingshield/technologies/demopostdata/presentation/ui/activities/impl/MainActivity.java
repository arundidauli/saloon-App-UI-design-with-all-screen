package com.wingshield.technologies.demopostdata.presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wingshield.technologies.demopostdata.R;
import com.wingshield.technologies.demopostdata.domain.executor.impl.ThreadExecutor;
import com.wingshield.technologies.demopostdata.model.Post;
import com.wingshield.technologies.demopostdata.presentation.presenters.PostDataPresenter;
import com.wingshield.technologies.demopostdata.presentation.ui.activities.PostView;
import com.wingshield.technologies.demopostdata.presentation.ui.adapters.CategoriesAdapter;
import com.wingshield.technologies.demopostdata.presentation.ui.adapters.PostAdapter;
import com.wingshield.technologies.demopostdata.presentation.ui.adapters.TopRatedAdapter;
import com.wingshield.technologies.demopostdata.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostView {

    private TextView txt_no_record_found;
    private ProgressBar progressBar;
    private List<Post> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv_top_rated = findViewById(R.id.rv_top_rated);
        RecyclerView rv_post = findViewById(R.id.rv_post);

        progressBar=findViewById(R.id.progress_circular);
        txt_no_record_found=findViewById(R.id.txt_no_record_found);
        postList=new ArrayList<>();

       //new PostDataPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),this).getAllPost();
        rv_post.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));
        rv_post.setAdapter(new CategoriesAdapter(MainActivity.this, postList));
        rv_top_rated.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv_top_rated.setAdapter(new TopRatedAdapter(MainActivity.this, postList));


        findViewById(R.id.iv_user_photo).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    @Override
    public void getPost(List<Post> postList) {
        progressBar.setVisibility(View.GONE);
        if (postList.size()>0){
                }else {
            txt_no_record_found.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Log.e(MainActivity.class.getSimpleName(),message);
    }
}