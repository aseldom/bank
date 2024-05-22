package com.effectivemobile.testtask.bank.service;

import com.effectivemobile.testtask.bank.dto.EmailAddDto;
import com.effectivemobile.testtask.bank.dto.EmailChangeDto;
import com.effectivemobile.testtask.bank.dto.EmailDeleteDto;
import com.effectivemobile.testtask.bank.dto.UserReturnDto;

public interface EmailService {

    UserReturnDto addEmail(String username, EmailAddDto emailAddDto);

    void deleteEmail(String username, EmailDeleteDto emailDeleteDto);

    UserReturnDto changeEmail(String userName, EmailChangeDto emailChangeDto);
}
