package com.deesha.medicine_distribution.service;

import com.deesha.medicine_distribution.model.CompanyAddress;
import com.deesha.medicine_distribution.model.UserModel;

import java.util.List;

public interface CompaniesAddressService  {

    boolean saveCompanyAddress(CompanyAddress companyAddress);
    List<UserModel> viewcompanyAddress();
}
