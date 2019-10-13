package com.raulbrumar.approximationofpi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    public static final int noOfDigits = 1000000;

    public static final int maxTimeMillis = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onButtonClick(View view)
    {
        // start Pi calculator asyncTask
        AsyncPi asyncPi = new AsyncPi();
        asyncPi.execute();
    }

    private class AsyncPi extends AsyncTask<Void, Void, String>
    {
        private int currentNoOfDigits = 0;
        private long startTime;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            startTime = System.currentTimeMillis();
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            String result = "";

            final BigInteger TWO = BigInteger.valueOf(2) ;
            final BigInteger THREE = BigInteger.valueOf(3) ;
            final BigInteger FOUR = BigInteger.valueOf(4) ;
            final BigInteger SEVEN = BigInteger.valueOf(7) ;

            BigInteger q = BigInteger.ONE ;
            BigInteger r = BigInteger.ZERO ;
            BigInteger t = BigInteger.ONE ;
            BigInteger k = BigInteger.ONE ;
            BigInteger n = BigInteger.valueOf(3) ;
            BigInteger l = BigInteger.valueOf(3) ;

            BigInteger nn, nr ;
            boolean first = true ;
            while(currentNoOfDigits <= noOfDigits && System.currentTimeMillis() - startTime <= maxTimeMillis)
            {
                if(FOUR.multiply(q).add(r).subtract(t).compareTo(n.multiply(t)) == -1)
                {
                    result += n.toString();
                    currentNoOfDigits++;

                    if(first){System.out.print(".") ; first = false ;}
                    nr = BigInteger.TEN.multiply(r.subtract(n.multiply(t))) ;
                    n = BigInteger.TEN.multiply(THREE.multiply(q).add(r)).divide(t).subtract(BigInteger.TEN.multiply(n)) ;
                    q = q.multiply(BigInteger.TEN) ;
                    r = nr ;

                    // flush the data here if needed

                }
                else
                {
                    nr = TWO.multiply(q).add(r).multiply(l) ;
                    nn = q.multiply((SEVEN.multiply(k))).add(TWO).add(r.multiply(l)).divide(t.multiply(l)) ;
                    q = q.multiply(k) ;
                    t = t.multiply(l) ;
                    l = l.add(TWO) ;
                    k = k.add(BigInteger.ONE) ;
                    n = nn ;
                    r = nr ;
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result)
        {
            // updates the UI
            super.onPostExecute(result);
            textView.setText(result);
        }
    }
}
