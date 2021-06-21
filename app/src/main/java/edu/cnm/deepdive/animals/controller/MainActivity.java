package edu.cnm.deepdive.animals.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.animals.BuildConfig;
import edu.cnm.deepdive.animals.R;
import edu.cnm.deepdive.animals.model.Animal;
import edu.cnm.deepdive.animals.service.WebServiceProxy;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

  private ImageView image;
  private Spinner animalSelector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    image = findViewById(R.id.image);
    animalSelector = findViewById(R.id.animal_selector);
    animalSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Animal animal = (Animal) parent.getItemAtPosition(position);
        Picasso.get().load(animal.getImageURL()).into(image);

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });
    new Retriever().start();

  }


  private class Retriever extends Thread {


    @Override
    public void run() {
      try {
        Response<List<Animal>> response = WebServiceProxy.getInstance()
            .getAnimals(BuildConfig.API_KEY)
            .execute();
        if (response.isSuccessful()) {
          //random animal generator

          List<Animal> animals = response.body();
          //assert animals != null;
          ArrayAdapter<Animal> adapter = new ArrayAdapter<>(MainActivity.this,
              R.layout.item_animal_spinner, animals);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
          runOnUiThread(() -> animalSelector.setAdapter(adapter));

        } else {
          Log.e(getClass().getName(), response.message());
        }
      } catch (IOException e) {
        Log.e(getClass().getName(), e.getMessage(), e);
      }

    }

  }
}
