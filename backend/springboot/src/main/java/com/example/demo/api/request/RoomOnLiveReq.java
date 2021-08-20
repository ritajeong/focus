package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomOnLiveReqest")
public class RoomOnLiveReq {
    @ApiModelProperty(example = "방 아이디")
    int room_id;
    @ApiModelProperty(example = "방의 현재 활동 상태")
    Boolean on_live;

    @Override
    public String toString() {
        return "RoomOnLiveReq{" +
                "room_id=" + room_id +
                ", on_live=" + on_live +
                '}';
    }
}
