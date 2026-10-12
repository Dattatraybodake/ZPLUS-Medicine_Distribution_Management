package com.deesha.medicine_distribution.service;

import com.deesha.medicine_distribution.model.Companies;
import com.deesha.medicine_distribution.model.UserModel;

import java.util.List;

public interface CompaniesService {

    boolean saveCompanies(Companies companies);
    List<UserModel> viewAllCompanies();
}
