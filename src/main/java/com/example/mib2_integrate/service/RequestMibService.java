package com.example.mib2_integrate.service;

import com.example.mib2_integrate.entity.City;
import com.example.mib2_integrate.entity.District;
import com.example.mib2_integrate.entity.Region;
import com.example.mib2_integrate.entity.RequestMib;
import com.example.mib2_integrate.payload.RequestDto;
import com.example.mib2_integrate.payload.ResponseDto;
import com.example.mib2_integrate.repository.CityRepository;
import com.example.mib2_integrate.repository.DistrictRepository;
import com.example.mib2_integrate.repository.RegionRepository;
import com.example.mib2_integrate.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestMibService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictRepository districtRepository;

    public HttpEntity<?> getPersonWithPnfl(RequestDto requestDto){

        try {
            ResponseDto responseDto = new ResponseDto();
            RequestMib requestMib = new RequestMib(
                    requestDto.getTransaction_id(),
                    requestDto.getSender_pinfl(),
                    requestDto.getPurpose(),
                    requestDto.getConsent(),
                    requestDto.getPinfl());
            requestRepository.save(requestMib);
            System.out.println(requestDto.getPinfl());

            Optional<Object[]> optionalResponseDto = requestRepository.getRequestMibPinfl(requestDto.getPinfl());
                Object[] objects = optionalResponseDto.get();
                if (objects.length > 0) {
                    for (Object object : objects) {
                        if (object instanceof Object[]) {
                            Object[] arrayObject = (Object[]) object;

                            Optional<Region> region;
                            Optional<District> district;
                            Optional<City> city;

                            String viloyat="topilmadi";
                            String tuman="topilmadi";
                            String shahar="topilmadi";

                            if (arrayObject[41] != null && arrayObject[41] instanceof Number) {
                                if (arrayObject[41]!=null) {
                                    Long regionId = ((Number) arrayObject[41]).longValue();
                                    region = regionRepository.findByIdRegion(Long.valueOf(regionId));
                                    viloyat = region.get().getName();
                                }
                                if (arrayObject[38]!=null) {
                                    Long districtId = ((Number) arrayObject[38]).longValue();
                                    district = districtRepository.findByIdDistrict(Long.valueOf(districtId));
                                    tuman = district.get().getName();
                                }
                                if (arrayObject[37]!=null) {
                                    Long cityId = ((Number) arrayObject[37]).longValue();
                                    city = cityRepository.findByIdCity(Long.valueOf(cityId));
                                    shahar = city.get().getName();
                                }
                        }
                            responseDto.setAddress("Viloyati: " + viloyat  +", Shahari: " + shahar + ", Tumani: "+ tuman);
                            responseDto.setFirst_name((String) arrayObject[14]);
                            responseDto.setPinfl((String) arrayObject[16]);
                            responseDto.setMiddle_name((String) arrayObject[12]);
                            responseDto.setLast_name((String) arrayObject[23]);
                            responseDto.setPassport_number((String) arrayObject[15]);
                            responseDto.setPassport_series((String) arrayObject[19]);
                            responseDto.setDate_of_birth((String) arrayObject[4]);
                        }
                    }
                    requestMib.setStatus(true);
                    requestRepository.save(requestMib);
                }
                return objects.length > 0
                        ? ResponseEntity.status(200).body(responseDto)
                        : ResponseEntity.status(404).body("Not Found");
        }
        catch (DataAccessException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(409).body(ex.getMessage());
        }
    }

}
