package com.deesha.medicine_distribution.serviceImpl;

import com.deesha.medicine_distribution.model.CompanyAddress;
import com.deesha.medicine_distribution.model.UserModel;
import com.deesha.medicine_distribution.service.CompaniesAddressService;

import java.util.List;

public class CompaniesAddressServiceImpl implements CompaniesAddressService {
    @Override
    public boolean saveCompanyAddress(CompanyAddress companyAddress) {
        return false;
    }

    @Override
    public List<UserModel> viewcompanyAddress() {
        return List.of();
    }
}
