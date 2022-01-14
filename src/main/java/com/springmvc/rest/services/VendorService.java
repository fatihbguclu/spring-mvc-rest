package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.model.VendorDTO;
import com.springmvc.rest.api.v1.model.VendorListDTO;
import org.springframework.stereotype.Service;

@Service
public interface VendorService {

    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
