package com.example.quanlykhachsan.dto;

import java.math.BigDecimal;

public class RoomDto {
    private Long roomId;
    private String code;
    private String type;
    private String status;
    private BigDecimal price;
    private String description;

    public RoomDto(){}

    // getters/setters
    public Long getRoomId(){ return roomId; }
    public void setRoomId(Long roomId){ this.roomId = roomId; }

    public String getCode(){ return code; }
    public void setCode(String code){ this.code = code; }

    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }

    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price = price; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
}
