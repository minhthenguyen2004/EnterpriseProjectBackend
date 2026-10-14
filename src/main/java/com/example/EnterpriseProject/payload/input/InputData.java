package com.example.EnterpriseProject.payload.input;

import com.example.EnterpriseProject.payload.dto.AoiDataDto;
import com.example.EnterpriseProject.payload.dto.KpsDto;
import com.example.EnterpriseProject.payload.dto.ScrewDataDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO nhận toàn bộ gói tin JSON từ các trạm máy tự động hóa (Ví dụ trạm ST3)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputData {

    private String data_type;

    private String carriercode;

    private String serialnumber;

    private String stationip;

    private String groupname;

    private String step;

    private String action;

    private BigDecimal cycletime;

    private String result;

    private String errorcode;

    private String username;

    private String password;

    @Builder.Default
    private List<KpsDto> kps = new ArrayList<>();

    private AoiDataDto aoidata;

    @Builder.Default
    private List<ScrewDataDto> screwdata = new ArrayList<>();

    /**
     * Tiện ích chuẩn hóa (trim) khoảng trắng cho các mã vạch quét từ cảm biến / barcode scanner
     */
    public String getCleanCarrierCode() {
        return carriercode != null ? carriercode.trim() : null;
    }

    public String getCleanSerialNumber() {
        return serialnumber != null ? serialnumber.trim() : null;
    }

    public String getCleanStationIp() {
        return stationip != null ? stationip.trim() : null;
    }

    public String getCleanGroupName() {
        return groupname != null ? groupname.trim() : null;
    }
}
