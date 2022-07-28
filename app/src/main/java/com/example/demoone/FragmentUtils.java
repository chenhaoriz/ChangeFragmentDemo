package com.example.demoone;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    private Fragment currentFragment;
    private final Fragment contentFragment;
    private final FirstFragment firstFragment;
    private final SecondFragment secondFragment;

    public FragmentUtils(Fragment contentFragment) {
        this.contentFragment = contentFragment;
        FragmentManager childFragmentManager = contentFragment.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        fragmentTransaction.add(R.id.frame_layout, firstFragment, firstFragment.getClass().getSimpleName());
        fragmentTransaction.add(R.id.frame_layout, secondFragment, secondFragment.getClass().getSimpleName());
        fragmentTransaction.hide(secondFragment).hide(firstFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public FragmentTransaction switchFragment(boolean isFirst) {
        return switchFragment(isFirst ? firstFragment : secondFragment);
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentManager childFragmentManager = contentFragment.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        if (targetFragment == null || targetFragment == currentFragment) {
            return fragmentTransaction;
        }
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment);
            }
            Fragment oldFragment = childFragmentManager.findFragmentByTag(targetFragment.getClass().getSimpleName());
            if (oldFragment != null) {
                fragmentTransaction.remove(oldFragment);
            }
            fragmentTransaction.add(R.id.frame_layout, targetFragment, targetFragment.getClass().getSimpleName()).show(targetFragment);

        } else if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment).show(targetFragment);
        } else {
            fragmentTransaction.show(targetFragment);
        }
        currentFragment = targetFragment;
        return fragmentTransaction;
    }


}
