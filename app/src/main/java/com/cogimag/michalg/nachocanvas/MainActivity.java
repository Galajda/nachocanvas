package com.cogimag.michalg.nachocanvas;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CanvasView canvasView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Spinner colorSpinner = (Spinner)findViewById(R.id.spnColorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(this);
//        canvasView = (CanvasView)findViewById(R.id.cnvLeinwand);
        canvasView = findViewById(R.id.cnvLeinwand);

        canvasView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        Log.i("canvas view", "on touch listener");
//                        return true;
                        return canvasView.handleTouch(event);
                    }
                }
        );
    }
    public void btnClearCanvas_Click(View view) {
//        Log.i("main activity", "btn clear click");
//        canvasView = (CanvasView)findViewById(R.id.cnvLeinwand);
        canvasView.clearCanvas();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("spinner selected", "item " + parent.getItemAtPosition(position));
        CanvasView.changeColor((String) parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
