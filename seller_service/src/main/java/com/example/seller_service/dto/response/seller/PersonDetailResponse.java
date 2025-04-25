package com.example.seller_service.dto.response.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDetailResponse {
    private String surname;
    private String name;
    private String patronimyc;
    private String phoneNumber;
}
