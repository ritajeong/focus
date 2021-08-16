package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomOnLiveReqest")
public class RoomOnLiveReq {
    @ApiModelProperty
    int room_id;
    @ApiModelProperty
    Boolean on_live;

    @Override
    public String toString() {
        return "RoomOnLiveReq{" +
                "room_id=" + room_id +
                ", on_live=" + on_live +
                '}';
    }
}
