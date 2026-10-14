package com.example.EnterpriseProject.enums;

/**
 * Trạng thái thời gian thực của đồ gá tuần hoàn (Carrier)
 */
public enum CarrierStatus {
    EMPTY,        // Đồ gá rỗng, sẵn sàng nhận phôi tại trạm đầu chuyền (ST1)
    BOUND,        // Đang kẹp/chở một phôi sản phẩm trên chuyền
    MAINTENANCE,  // Đang bảo dưỡng / hiệu chuẩn định kỳ
    LOCKED        // Bị khóa do lỗi cơ khí / cong vênh / mòn ren
}
