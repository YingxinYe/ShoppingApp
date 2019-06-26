package android.example.june20_shoppingapp.adapters;

import android.example.june20_shoppingapp.fragments.ProductFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ProductFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragment=new ArrayList<>();
    private ArrayList<String> mTitle=new ArrayList<>();

    public ProductFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public void addFragment(String title){
        mFragment.add(ProductFragment.newInstance(title));   //链接fragment与activity
        mTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
