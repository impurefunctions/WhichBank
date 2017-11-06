package bw.co.whichbank.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;

import bw.co.whichbank.R;
import bw.co.whichbank.models.Bank;
import bw.co.whichbank.models.LoanPaymentCalculator;
import bw.co.whichbank.util.Constants;

public class BankResults extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
  public static List<Bank> bankList;
    public static List<Bank> qualifyingBanks;
    public static int fragCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_results);
        Constants.populateBanks();
        Constants.populateQualifyingBanks();
        bankList = Constants.bankList;
        qualifyingBanks = Constants.qualifyingBanks;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        fragCount = mSectionsPagerAdapter.getCount();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bank_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_BANK_NAME = "Bank_Name";
        private static final String ARG_MONTHLY_PAYMENTS = "Monthly_Payments";
        private static final String ARG_PRINCIPAL = "Principal";
        private static final String ARG_INTEREST = "Interest";
        TextView tvDisq;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bank bank = qualifyingBanks.get(sectionNumber);
            Log.d("Results: ", String.valueOf(qualifyingBanks.size()));
            Bundle args = new Bundle();
            args.putString(ARG_BANK_NAME, bank.bankName);
            double baseEMI = LoanPaymentCalculator.calculateMonthlyPayment(Constants.loanAmount,4,0.14);
            args.putString(ARG_MONTHLY_PAYMENTS, String.valueOf(baseEMI));
            args.putString(ARG_PRINCIPAL, String.valueOf(Constants.loanAmount));
            args.putString(ARG_INTEREST, String.valueOf(bank.baseRate * Constants.loanAmount));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bank_results, container, false);
            TextView tvBankName = rootView.findViewById(R.id.section_bank_name);
            tvDisq = rootView.findViewById(R.id.disqualified);
            TextView tvMonthlyPayments = rootView.findViewById(R.id.tvMonthlyPayments);
            TextView tvPrincipal = rootView.findViewById(R.id.tvPrincipal);
            TextView tvTotalInterest = rootView.findViewById(R.id.tvTotalInterest);
           if (fragCount==0){
               tvDisq.setVisibility(View.VISIBLE);
           }else{
               tvBankName.setText(getArguments().get(ARG_BANK_NAME).toString());
               tvPrincipal.setText(getArguments().get(ARG_PRINCIPAL).toString());
               tvMonthlyPayments.setText(getArguments().get(ARG_MONTHLY_PAYMENTS).toString());
               tvTotalInterest.setText(getArguments().get(ARG_INTEREST).toString());
           }




            // tvBankName.setText(getString(R.string.section_format, getArguments().get(ARG_BANK_NAME)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {

            return Constants.qualifyingBanks.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return qualifyingBanks.get(position).bankName;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Constants.bankList.clear();
        Constants.qualifyingBanks.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
