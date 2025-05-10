package com.example.order_service.model.invariant;

public enum OrderItemStatus {
    NEW,// Новый заказ, только оформлен
    PAID,// Оплачен
    PROCESSING,// В обработке
    IN_TRANSIT,// В пути к пункту назначения
    AT_PICKUP_POINT,// Прибыл в пункт выдачи
    DELIVERED,// Получен покупателем
    CANCELLED,// Отменён
    RETURN_REQUESTED,// Ожидается возврат
    RETURNED,// Возвращён
    REFUNDED// Деньги возвращены
}
