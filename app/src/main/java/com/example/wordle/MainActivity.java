 package com.example.wordle;

 import androidx.activity.result.ActivityResult;
 import androidx.activity.result.ActivityResultCallback;
 import androidx.activity.result.ActivityResultLauncher;
 import androidx.activity.result.contract.ActivityResultContracts;
 import androidx.appcompat.app.AppCompatActivity;

 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener
 {
     private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonStart = findViewById(R.id.buttonStart);

        this.buttonStart.setOnClickListener(this);
    }

    ActivityResultLauncher<Intent> getData = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result != null)
                    {

                    }
                }
            });

    @Override
    public void onClick(View view)
    {
        if (view != null)
        {
            Intent intent;
            if (view.getId() == this.buttonStart.getId())
            {
                intent = new Intent(this, WordleActivity.class);
                getData.launch(intent);
            }

            else
            {
                Log.e("-MainActivity onClick-", "unresolved button");
            }
        }
    }
}