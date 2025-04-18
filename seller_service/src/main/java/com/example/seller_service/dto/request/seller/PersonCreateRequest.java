package com.example.seller_service.dto.request.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonCreateRequest {
    private String surname;
    private String name;
    private String patronimyc;
    private String phoneNumber;
}
