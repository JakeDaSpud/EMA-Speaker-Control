package trier.hochschule.ema.speakercontrol;

import android.content.Context;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import trier.hochschule.ema.speakercontrol.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addSlaves();
    }

    public void addSlaves() {

        // Add all the Slaves to the Masters
        binding.allToggleButton.addSlaves(
                binding.centerToggleButton,
                binding.subwooferToggleButton,
                binding.frontToggleButton,
                binding.rearToggleButton
        );

        binding.frontToggleButton.addSlaves(
                binding.frontLeftToggleButton,
                binding.frontRightToggleButton
        );

        binding.rearToggleButton.addSlaves(
                binding.rearLeftToggleButton,
                binding.rearRightToggleButton
        );
    }
}
