package trier.hochschule.ema.speakercontrol;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MasterToggleButton extends androidx.appcompat.widget.AppCompatToggleButton {

    final List<ToggleButton> slaves = new ArrayList<>();
    boolean initialised = false;

    public MasterToggleButton(Context context) {
        super(context);
        initialised = true;
    }

    public MasterToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialised = true;
    }

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialised = true;
    }

    /// @param slaves The ToggleButtons to be controlled by this MasterToggleButton
    /// Adds given ToggleButtons to slaves List
    public void addSlaves(ToggleButton... slaves) {
        for (int i = 0; i < slaves.length; i++) {
            this.slaves.add(slaves[i]);
        }
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
}
