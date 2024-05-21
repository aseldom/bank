package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.AddPhoneDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

public interface PhoneService {

    UserReturnDto addPhone(String username, AddPhoneDto addPhoneDto);

    void deletePhone(String username, String phone);

}
