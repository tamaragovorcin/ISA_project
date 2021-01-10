package com.isaproject.isaproject.psw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.repository.PharmacyRepository;

@Service
public class PharmacyService implements IPharmacyService{

	 @Autowired
	    private PharmacyRepository repository;

	    @Override
	    public boolean add(Pharmacy pharmacy)
	    {
	        if(get(pharmacy.getApiKey()) == null)
	        {
	        	pharmacy.setApiKey(("api" + pharmacy.getName() + "1234").replace(' ', '_'));
	            repository.save(pharmacy);
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public boolean remove(String apiKey)
	    {
	    	 Pharmacy pharmacy = get(apiKey);
		        if(pharmacy == null)
		        {
		            return false;
		        }
		        repository.delete(pharmacy);
		        return true;
	    }

		@Override
		public boolean update(Pharmacy pharmacy) {
			  if(get(pharmacy.getApiKey()) == null) return false;
		        repository.save(pharmacy);
		        return true;
		}

		@Override
		public Pharmacy get(String apiKey) {
			return repository.findById(apiKey).orElse(null);
		}

		@Override
		public List<Pharmacy> getAll() {
			 return (List<Pharmacy>) repository.findAll();
		}

	 

	

}
