package com.example.order_service.service.impl;

import com.example.order_service.dto.request.pickup_point.PickupPointCreateRequest;
import com.example.order_service.dto.request.pickup_point.PickupPointUpdateRequest;
import com.example.order_service.exception.UserNotFoundException;
import com.example.order_service.model.PickupPoint;
import com.example.order_service.repository.PickupPointRepository;
import com.example.order_service.service.PickupPointService;
import com.example.order_service.service.mapper.PickupPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PickupPointServiceImpl implements PickupPointService {

    private final PickupPointRepository pickupPointRepository;
    private final PickupPointMapper pickupPointMapper;

    @Autowired
    public PickupPointServiceImpl(PickupPointRepository pickupPointRepository, PickupPointMapper pickupPointMapper) {
        this.pickupPointRepository = pickupPointRepository;
        this.pickupPointMapper = pickupPointMapper;
    }

    @Override
    @Transactional
    public PickupPoint save(PickupPoint pickupPoint) {
        return pickupPointRepository.save(pickupPoint);
    }

    @Override
    @Transactional
    public PickupPoint create(PickupPointCreateRequest data) {

        PickupPoint pickupPoint = pickupPointMapper.fromPickupPointCreateRequest(data);

        return save(pickupPoint);
    }

    @Override
    @Transactional(readOnly = true)
    public PickupPoint getById(UUID id) {
        return pickupPointRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Такой пользователь не найден.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PickupPoint getFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID pickupPointId = (UUID) auth.getCredentials();
        return getById(pickupPointId);
    }

    @Override
    @Transactional
    public PickupPoint update(PickupPointUpdateRequest data) {

        PickupPoint pickupPoint = getFromAuth();
        pickupPointMapper.updateFromPickupPointUpdateRequest(data,pickupPoint);

        return save(pickupPoint);
    }
}
