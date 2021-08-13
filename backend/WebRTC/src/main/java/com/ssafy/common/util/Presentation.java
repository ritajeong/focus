package com.ssafy.common.util;

public class Presentation {
	private String presenterName;
	private String presentationImageUri;
	private String presentationLocation;
	private String presentationSize;

	public Presentation(String presenterName, String presentationImageUri, String presentationLocation,
			String presentationSize) {
		this.presenterName = presenterName;
		this.presentationImageUri = presentationImageUri;
		this.presentationLocation = presentationLocation;
		this.presentationSize = presentationSize;
	}

	public String getPresenterName() {
		return presenterName;
	}

	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}

	public String getPresentationImageUri() {
		return presentationImageUri;
	}

	public void setPresentationImageUri(String presentationImageUri) {
		this.presentationImageUri = presentationImageUri;
	}

	public String getPresentationLocation() {
		return presentationLocation;
	}

	public void setPresentationLocation(String presentationLocation) {
		this.presentationLocation = presentationLocation;
	}

	public String getPresentationSize() {
		return presentationSize;
	}

	public void setPresentationSize(String presentationSize) {
		this.presentationSize = presentationSize;
	}

}
