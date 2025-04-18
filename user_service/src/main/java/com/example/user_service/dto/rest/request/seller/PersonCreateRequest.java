package com.example.user_service.dto.rest.request.seller;

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
