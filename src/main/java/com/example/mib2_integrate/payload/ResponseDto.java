package com.example.mib2_integrate.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String last_name;//14
    private String first_name;//23
    private String middle_name;//12
    private String date_of_birth;//4
    private String passport_series;//19
    private String passport_number;//15
    private String pinfl;//16
    private String address;//region:41, district:38, city 37


}
