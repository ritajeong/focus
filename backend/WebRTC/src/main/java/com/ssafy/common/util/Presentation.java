package com.ssafy.common.util;

import org.kurento.client.MediaPipeline;

public class Presentation {
	private String presenterName;
	private String[] presentationImageUris;
	private MediaPipeline pipeline;

	public Presentation() {
	}

	public Presentation(String presenterName, String[] imageUris, String roomName, MediaPipeline pipeline) {
		this.presenterName = presenterName;
		this.presentationImageUris = imageUris;
		this.pipeline = pipeline;
	}

	public String getPresenterName() {
		return presenterName;
	}

	public String[] getPresentationImages() {
		return presentationImageUris;
	}

	public MediaPipeline getPipeline() {
		return pipeline;
	}
}
