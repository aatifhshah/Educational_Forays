package com.example.aatifshah.cs161_hmk1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView monthlyPayment, interestRateVal, interestRateLabel;
    private EditText amountBorrowed;
    private SeekBar interestRate;
    private RadioGroup loanTerm;
    private CheckBox taxesAndInsurance;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        monthlyPayment = (TextView) findViewById(R.id.monthly_payment_textview);
        amountBorrowed = (EditText) findViewById(R.id.amount_borrowed);
        interestRate = (SeekBar) findViewById(R.id.annual_rate_of_interest_seekbar);
        interestRateVal = (TextView) findViewById(R.id.interest_rate_value);
        interestRateLabel = (TextView) findViewById(R.id.interest_rate_label);
        loanTerm = (RadioGroup) findViewById(R.id.loanterm_radiogroup);
        taxesAndInsurance = (CheckBox) findViewById(R.id.taxes_insurances_checkbox);
        calculate = (Button) findViewById(R.id.calculate_button);


        interestRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                interestRateLabel.setVisibility(View.INVISIBLE);
                int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
                int thumbPos = seekBar.getPaddingLeft() + width * seekBar.getProgress() / seekBar.getMax();
                interestRateVal.setText(String.valueOf(getInterestRate(progress)));
                interestRateVal.setX(thumbPos - interestRateVal.getPaint().measureText(interestRateVal.getText().toString())/2 );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                interestRateVal.setVisibility(View.VISIBLE);
                interestRateLabel.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interestRateVal.setVisibility(View.INVISIBLE);
                interestRateLabel.setVisibility(View.VISIBLE);
                interestRateLabel.setHint(getInterestRate(seekBar.getProgress()) + "% APR");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMonthlyPayment();
            }
        });
    }

    private void calculateMonthlyPayment() {
        double m = 0;
        if(taxesAndInsurance.isChecked() && formIsValid()){
            //big formula
            double p = Double.valueOf(amountBorrowed.getText().toString());
            double j = (Double.valueOf(getInterestRate(interestRate.getProgress()))/1200D);
            int n = Integer.parseInt(((RadioButton) findViewById(loanTerm.getCheckedRadioButtonId())).getHint().toString()) * 12;
            double t = p * 0.001;
            m = (p * ( j / (1 - Math.pow((1+j),-n)))) + t;

        } else if(formIsValid()) {
            //small formula
            double p = Double.valueOf(amountBorrowed.getText().toString());
            int n = Integer.parseInt(((RadioButton) findViewById(loanTerm.getCheckedRadioButtonId())).getHint().toString()) * 12;
            double t = p * 0.001;
            m = (p/n) + t;
        } else {
            //present toast
            Toast toast = Toast.makeText(getApplicationContext(), "Fill out form please!", Toast.LENGTH_SHORT);
            toast.show();
        }

        setMortgageLabel(monthlyPayment, m);
    }


    private boolean formIsValid() {
        return amountBorrowed.getText().toString().length() > 0 && loanTerm.getCheckedRadioButtonId() != -1;
    }

    private String getInterestRate(int progress) {
        DecimalFormat f = new DecimalFormat("#0.0");
        return String.valueOf(f.format(progress*0.1));
    }

    private void setMortgageLabel(final TextView label, double value){
        ValueAnimator enlargeAnimation = ValueAnimator.ofFloat(40f, 50f);
        enlargeAnimation.setInterpolator(new AccelerateInterpolator());
        enlargeAnimation.setDuration(200);

        final ValueAnimator animator = ValueAnimator.ofFloat(0f, (float) value);
        animator.setInterpolator(new DecelerateInterpolator(1.5f));
        animator.setDuration(3000);

        final ValueAnimator minimizeAnimation = ValueAnimator.ofFloat(50f, 40f);
        minimizeAnimation.setInterpolator(new DecelerateInterpolator());
        minimizeAnimation.setDuration(200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            DecimalFormat f = new DecimalFormat("#,##0.00");
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                label.setText("$"+f.format(animation.getAnimatedValue())+"/mo");
            }
        });

        enlargeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                label.setTextSize((float)animation.getAnimatedValue());
            }
        });

        minimizeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                label.setTextSize((float)animation.getAnimatedValue());
            }
        });

        enlargeAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator.start();
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                minimizeAnimation.start();
            }
        });

        enlargeAnimation.start();

    }

}
