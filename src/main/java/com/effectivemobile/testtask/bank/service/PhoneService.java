package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.PhoneAddDto;
import com.effectivemobile.testtask.bank.dto.PhoneChangeDto;
import com.effectivemobile.testtask.bank.dto.PhoneDeleteDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

public interface PhoneService {

    UserReturnDto addPhone(String username, PhoneAddDto phoneAddDto);

    void deletePhone(String username, PhoneDeleteDto phoneDeleteDto);

    UserReturnDto changePhone(String userName, PhoneChangeDto phoneChangeDto);

}
