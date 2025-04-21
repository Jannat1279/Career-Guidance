package com.example.CareerGuidance.CareerGuidance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CareerGuidance.CareerGuidance.model.Register;
import com.example.CareerGuidance.CareerGuidance.repository.RegisterRepository;

import lombok.extern.slf4j.Slf4j;
@Service
public class RegisterService {
	  @Autowired
	    private RegisterRepository registerRepo;

	  @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

	    public boolean saveMessageDetails(Register register) {
	        // Log the registration attempt
	        log.info("Attempting to register user: {}", register.toString());

	        try {
	        	 String encodedPassword = passwordEncoder.encode(register.getPassword());
	             register.setPassword(encodedPassword);  
	            // Save the register object to the database
	            registerRepo.save(register);
	            // Log success after saving
	            log.info("User successfully registered: {}", register.toString());
	            return true; // Indicating successful save
	        } catch (Exception e) {
	            // Log any error that occurs
	            log.error("Error saving user: {}", register.toString(), e);
	            return false; // Indicating failure
	        }
	    }
    
    
//    public boolean saveMessageDetails(Register register) {
//        // You could add null/validation checks here
//        registerRepo.save(register);
//        return true;
//    }
}


//private static final Logger log = LoggerFactory.getLogger(RegisterService.class);
/**
 * Save Contact Details into DB
 * @param contact
 * @return boolean
 */
//public boolean saveMessageDetails(Register register){
//    boolean isSaved = true;
//    //TODO - Need to persist the data into the DB table
////    log.info(register.toString());
////    return isSaved;
//    registerRepo.save(register); // <-- this line is crucial
//    return true;
//}
////public boolean saveMessageDetails(Register register) {
//    registerRepo.save(register); // <-- this line is crucial
//    return true;
//}


//    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);
//
//    @Autowired
//    private RegisterRepository registerRepo;
//
//    public boolean saveMessageDetails(Register register){
//        log.info("Saving register details: {}", register);
//        registerRepo.save(register);
//        return true;
//    }

//}