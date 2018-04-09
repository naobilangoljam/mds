package com.kangleiit.manipuridictionary.ui.login;

import com.kangleiit.manipuridictionary.ui.base.BasePresenterInterface;

/**
 * Created by Naobi on 08/04/2018.
 */

public interface LoginPresInterface extends BasePresenterInterface {
    void login(String email,String password,String imgUrl);
}
