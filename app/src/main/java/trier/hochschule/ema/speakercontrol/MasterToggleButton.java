package trier.hochschule.ema.speakercontrol;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MasterToggleButton extends androidx.appcompat.widget.AppCompatToggleButton {

    final List<ToggleButton> slaves = new ArrayList<>();
    boolean initialised = false;
    private boolean isDisabledSlaves;

    public MasterToggleButton(Context context) {
        super(context);
        initialised = true;
    }

    public MasterToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyleToggle);;
    }

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // Using this SO post as reference
        // https://stackoverflow.com/questions/33163042/reading-android-attributes-on-my-custom-view
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MasterToggleButton,
                defStyleAttr,
                0
        );

        isDisabledSlaves = a.getBoolean(R.styleable.MasterToggleButton_disableSlaves, false);

        initialised = true;
    }

    /// @param slaves The ToggleButtons to be controlled by this MasterToggleButton
    /// Adds given ToggleButtons to slaves List
    public void addSlaves(ToggleButton... slaves) {
        for (int i = 0; i < slaves.length; i++) {
            this.slaves.add(slaves[i]);
        }

        // If isDisabledSlaves, then the isClickable of children should be false
        setDisabledSlaves(this.isDisabledSlaves);
    }

    /// Removes all slaves from slaves List
    public void removeSlaves() {
        this.slaves.clear();
    }

    /// Set all slaves to be the same checked state
    @Override
    public void setChecked(boolean checked) {
        if (!initialised) {
            return;
        }

        super.setChecked(checked);

        for (ToggleButton tb : slaves) {
            tb.setChecked(checked);
        }

    }

    public boolean isDisableSlaves() {
        return this.isDisabledSlaves;
    }

    public void setDisabledSlaves(boolean disable) {
        this.isDisabledSlaves = disable;

        for (ToggleButton tb : slaves) {
            tb.setClickable(!this.isDisabledSlaves);
        }
    }

}
