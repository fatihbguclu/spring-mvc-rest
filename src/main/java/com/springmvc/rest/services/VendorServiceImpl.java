package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.mapper.VendorMapper;
import com.springmvc.rest.api.v1.model.VendorDTO;
import com.springmvc.rest.api.v1.model.VendorListDTO;
import com.springmvc.rest.controller.v1.VendorController;
import com.springmvc.rest.domain.Vendor;
import com.springmvc.rest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {

        List<VendorDTO> vendorDTOList = vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOList);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        return saveAndReturnDto(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDto(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    return saveAndReturnDto(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDto(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDTO;
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/" + id;
    }
}
