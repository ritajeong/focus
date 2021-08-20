package com.ssafy.common.util;

public class Presentation {
	private String presentationUserId;
	private String presenterName;
	private String presentationTransition;
	private String presentationCurrentPage;
	private String presentationLocation;
	private String presentationSize;

	public Presentation(String presentationUserId, String presenterName, String presentationTransition,
			String presentationCurrentPage, String presentationLocation, String presentationSize) {
		this.presentationUserId = presentationUserId;
		this.presenterName = presenterName;
		this.presentationTransition = presentationTransition;
		this.presentationCurrentPage = presentationCurrentPage;
		this.presentationLocation = presentationLocation;
		this.presentationSize = presentationSize;
	}

	public String getPresentationUserId() {
		return presentationUserId;
	}

	public void setPresentationUserId(String presentationUserId) {
		this.presentationUserId = presentationUserId;
	}

	public String getPresenterName() {
		return presenterName;
	}

	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}

	public String getPresentationTransition() {
		return presentationTransition;
	}

	public void setPresentationTransition(String presentationTransition) {
		this.presentationTransition = presentationTransition;
	}

	public String getPresentationCurrentPage() {
		return presentationCurrentPage;
	}

	public void setPresentationCurrentPage(String presentationCurrentPage) {
		this.presentationCurrentPage = presentationCurrentPage;
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

	@Override
	public String toString() {
		return "Presentation [presentationUserId=" + presentationUserId + ", presenterName=" + presenterName
				+ ", presentationTransition=" + presentationTransition + ", presentationCurrentPage="
				+ presentationCurrentPage + ", presentationLocation=" + presentationLocation + ", presentationSize="
				+ presentationSize + "]";
	}

}
