package com.example.seller_service.dto.request.seller.put;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDetailUpdateRequest {
    private String surname;
    private String name;
    private String patronimyc;
    private String phoneNumber;
}
