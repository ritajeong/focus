package com.ssafy.api.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kurento.client.ImageOverlayFilter;
import org.kurento.client.MediaPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssafy.common.util.Presentation;
import com.ssafy.common.util.Room;
import com.ssafy.common.util.UserSession;

public class PresentationManager {
	private final Logger log = LoggerFactory.getLogger(RoomManager.class);

	/*
	 * key: [방 이름]-[발표자 이름] value: 발표자료, pipeline , 등등
	 */
	private final ConcurrentMap<String, Presentation> presentations = new ConcurrentHashMap<>();

	// TODO 임시자료 -> 나중에는 db에서 경로 가져와서 Presentation.class - presentationImageUris 에
	// 저장되어야함
	private String[] imageUris = { "/home/ubuntu/presentations/kim/001.jpg", "/home/ubuntu/presentations/kim/002.jpg",
			"/home/ubuntu/presentations/kim/003.jpg", "/home/ubuntu/presentations/kim/004.jpg",
			"/home/ubuntu/presentations/kim/005.jpg", "/home/ubuntu/presentations/kim/006.jpg",
			"/home/ubuntu/presentations/kim/007.jpg", "/home/ubuntu/presentations/kim/008.jpg", };
	private int imageIndex;
	private Presentation presentation;
	private ImageOverlayFilter imageOverlayFilter;
	private UserSession presenter;

	// ImageOverlayFilter 위치, 크기 설정 변수
	private float originOffsetXPercent = 0.02f;
	private float originOffsetYPercent = 0.25f;
	private float originWidthPrecent = 0.64f;
	private float originHeightPrecent = 0.56f;

	private float offsetXPercent = originOffsetXPercent;
	private float offsetYPercent = originOffsetYPercent;
	private float widthPrecent = originWidthPrecent;
	private float heightPrecent = originHeightPrecent;

	private boolean keepAspectRatio = false;
	private boolean imageCenter = true;
	private boolean isFullScreen = false;

	public Presentation getPresentation(String presenterName, Room room, UserSession presenter) {
		String key = room.getName() + "-" + presenterName;
		presentation = presentations.get(key);

		MediaPipeline pipeline = room.getPipeline();
		if (presentation == null) {
			presentation = new Presentation(presenterName, imageUris, room.getName(), pipeline);
			presentations.put(key, presentation);
			this.presenter = presenter;
			imageOverlayFilter = new ImageOverlayFilter.Builder(presentation.getPipeline()).build();

		}
		return presentation;
	}

	public void setPresenter(boolean isPresenter) throws IOException {
		presenter.setPresenter(isPresenter);
	}

	public UserSession getPresenter() {
		return presenter;
	}
	
	public ImageOverlayFilter getImageOverlayFilter() {
		return imageOverlayFilter;
	}

	public void changeImageLocation(String location) {
		switch (location) {
		case "left":
			offsetXPercent = 0.02f;
			offsetYPercent = 0.25f;
			widthPrecent = 0.64f;
			heightPrecent = 0.56f;
			break;
		case "right": {
			offsetXPercent = 0.34f;
			offsetYPercent = 0.25f;
			widthPrecent = 0.64f;
			heightPrecent = 0.56f;
			break;
		}
		case "top": {
			offsetXPercent = 0.2f;
			offsetYPercent = 0.02f;
			widthPrecent = 0.64f;
			heightPrecent = 0.56f;
			break;
		}
		default:
			break;
		}

		originOffsetXPercent = offsetXPercent;
		originOffsetYPercent = offsetYPercent;
		originWidthPrecent = widthPrecent;
		originHeightPrecent = heightPrecent;

		String removeImageId = "testImage" + imageIndex;
		String addImageId = "testImage" + imageIndex;
		String addImageUri = imageUris[imageIndex];

		imageOverlayFilter.removeImage(removeImageId);
		imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPrecent,
				heightPrecent, keepAspectRatio, imageCenter);

	}

	public void start() {
		imageIndex = 0;
		String imageId = "testImage" + imageIndex;
		String imageUri = imageUris[imageIndex];

		imageOverlayFilter.addImage(imageId, imageUri, offsetXPercent, offsetYPercent, widthPrecent, heightPrecent,
				keepAspectRatio, imageCenter);
		log.info("[start] imageId: {}, imageUri: {}", imageId, imageUri);

		presenter.getOutgoingWebRtcPeer().connect(imageOverlayFilter);
		imageOverlayFilter.connect(presenter.getIncomingMedia(presenter.getName()));
	}

	public void prev() {
		if (imageIndex > 0) {
			String removeImageId = "testImage" + imageIndex;

			imageIndex--;
			String addImageId = "testImage" + imageIndex;
			String addImageUri = imageUris[imageIndex];
			imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPrecent,
					heightPrecent, keepAspectRatio, imageCenter);
			imageOverlayFilter.removeImage(removeImageId);

		} else {
			log.info("[prev] 맨 처음 사진입니다.");
		}
	}

	public void next() {
		if (imageIndex < imageUris.length - 1) {
			String removeImageId = "testImage" + imageIndex;

			imageIndex++;
			String addImageId = "testImage" + imageIndex;
			String addImageUri = imageUris[imageIndex];
			imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPrecent,
					heightPrecent, keepAspectRatio, imageCenter);
			imageOverlayFilter.removeImage(removeImageId);

		} else {
			log.info("[next] 마지막 사진입니다.");
		}
	}

	public void full() {
		if (isFullScreen) {
			offsetXPercent = originOffsetXPercent;
			offsetYPercent = originOffsetYPercent;
			widthPrecent = originWidthPrecent;
			heightPrecent = originHeightPrecent;
			isFullScreen = false;
		} else {
			offsetXPercent = 0.0f;
			offsetYPercent = 0.0f;
			widthPrecent = 1.0f;
			heightPrecent = 1.0f;
			isFullScreen = true;
		}

		log.info("[full toggle] isFullScreen: {}", isFullScreen);

		String imageId = "testImage" + imageIndex;
		String imageUri = imageUris[imageIndex];
		imageOverlayFilter.removeImage(imageId);
		imageOverlayFilter.addImage(imageId, imageUri, offsetXPercent, offsetYPercent, widthPrecent, heightPrecent,
				keepAspectRatio, imageCenter);

	}
}
