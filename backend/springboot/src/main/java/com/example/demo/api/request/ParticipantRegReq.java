package com.example.demo.api.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ParticipantsRegRequest")
public class ParticipantRegReq {
	@ApiModelProperty
	List<String> person;
	@ApiModelProperty
	int roon_id;

	@Override
	public String toString() {
		return "ParticipantRegReq{" +
				"person=" + person +
				", roon_id=" + roon_id +
				'}';
	}
}
