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
	private float originwidthPercent = 0.64f;
	private float originheightPercent = 0.56f;

	private float offsetXPercent = originOffsetXPercent;
	private float offsetYPercent = originOffsetYPercent;
	private float widthPercent = originwidthPercent;
	private float heightPercent = originheightPercent;

	private boolean keepAspectRatio = false;
	private boolean imageCenter = true;
	private boolean isFullScreen = false;

	public Presentation getPresentation(Room room, UserSession presenter) {
		String key = room.getName() + "-" + presenter.getName();
		presentation = presentations.get(key);

		MediaPipeline pipeline = presenter.getPipeline();
		if (presentation == null) {
//			presentation = new Presentation(presenter.getName(), imageUris, room.getName(), pipeline);
			presentations.put(key, presentation);
			this.presenter = presenter;
//			imageOverlayFilter = new ImageOverlayFilter.Builder(presentation.getPipeline()).build();
		}
		return presentation;
	}
	
	public void removePresentation(Room room, UserSession presenter) {
		String key = room.getName() + "-" + presenter.getName();
		presentations.remove(key);
	}

	public void setPresenter() throws IOException {
		presenter.setPresenter();
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
			widthPercent = 0.64f;
			heightPercent = 0.56f;
			break;
		case "right": {
			offsetXPercent = 0.34f;
			offsetYPercent = 0.25f;
			widthPercent = 0.64f;
			heightPercent = 0.56f;
			break;
		}
		case "top": {
			offsetXPercent = 0.2f;
			offsetYPercent = 0.02f;
			widthPercent = 0.64f;
			heightPercent = 0.56f;
			break;
		}
		default:
			break;
		}

		originOffsetXPercent = offsetXPercent;
		originOffsetYPercent = offsetYPercent;
		originwidthPercent = widthPercent;
		originheightPercent = heightPercent;

		String removeImageId = "testImage" + imageIndex;
		String addImageId = "testImage" + imageIndex;
		String addImageUri = imageUris[imageIndex];

		imageOverlayFilter.removeImage(removeImageId);
		imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPercent,
				heightPercent, keepAspectRatio, imageCenter);

	}

	public void start(UserSession user) throws IOException {
		imageIndex = 0;
		String imageId = "testImage" + imageIndex;
		String imageUri = imageUris[imageIndex];

		log.info("[start] imageId: {}, imageUri: {}", imageId, imageUri);
		imageOverlayFilter.addImage(imageId, imageUri, offsetXPercent, offsetYPercent, widthPercent, heightPercent,
				keepAspectRatio, imageCenter);

		presenter.getOutgoingWebRtcPeer().connect(imageOverlayFilter);
		imageOverlayFilter.connect(presenter.getIncomingMedia(presenter.getName()));
	}

	public void prev() {
		if (imageIndex > 0) {
			String removeImageId = "testImage" + imageIndex;

			imageIndex--;
			String addImageId = "testImage" + imageIndex;
			String addImageUri = imageUris[imageIndex];
			imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPercent,
					heightPercent, keepAspectRatio, imageCenter);
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
			imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPercent,
					heightPercent, keepAspectRatio, imageCenter);
			imageOverlayFilter.removeImage(removeImageId);

		} else {
			log.info("[next] 마지막 사진입니다.");
		}
	}
	
//	public void next() {
//		if (imageIndex < imageUris.length - 1) {
//			smallOut();
//		} else {
//			log.info("[next] 마지막 사진입니다.");
//		}
//	}
//
//	private void smallOut() {
//		String removeImageId = "testImage" + imageIndex;
//		String removeImageUri = imageUris[imageIndex];
//
//		imageIndex++;
//		String addImageId = "testImage" + imageIndex;
//		String addImageUri = imageUris[imageIndex];
//		int offset = 5;
//		float floatOffset = 1.0f / offset;
//		for (int i = 1; i <= offset; i++) {
//			if (i == 1) {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri,
//						offsetXPercent + widthPercent * (floatOffset * i / 2),
//						offsetYPercent + heightPercent * (floatOffset * i / 2), widthPercent * (1 - floatOffset * i),
//						heightPercent * (1 - floatOffset * i), keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage(removeImageId);
//			} else if (i == offset) {
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//			} else {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri,
//						offsetXPercent + widthPercent * (floatOffset * i / 2),
//						offsetYPercent + heightPercent * (floatOffset * i / 2), widthPercent * (1 - floatOffset * i),
//						heightPercent * (1 - floatOffset * i), keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//			}
//		}
//		for (int i = offset; i >= 1; i--) {
//			if (i == offset) {
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri,
//						offsetXPercent + widthPercent * (floatOffset * i / 2),
//						offsetYPercent + heightPercent * (floatOffset * i / 2), widthPercent * (1 - floatOffset * i),
//						heightPercent * (1 - floatOffset * i), keepAspectRatio, imageCenter);
//			} else if (i == 1) {
//				imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent, offsetYPercent, widthPercent,
//						heightPercent, keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//			} else {
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri,
//						offsetXPercent + widthPercent * (floatOffset * i / 2),
//						offsetYPercent + heightPercent * (floatOffset * i / 2), widthPercent * (1 - floatOffset * i),
//						heightPercent * (1 - floatOffset * i), keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//			}
//		}
//	}
//
//	private void moveDown() {
//		String removeImageId = "testImage" + imageIndex;
//		String removeImageUri = imageUris[imageIndex];
//
//		imageIndex++;
//		String addImageId = "testImage" + imageIndex;
//		String addImageUri = imageUris[imageIndex];
//		int offset = 5;
//		float floatOffset = 1.0f / offset;
//		for (int i = 1; i <= offset; i++) {
//			if (i == 1) {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri, offsetXPercent,
//						offsetYPercent + floatOffset * i, widthPercent, heightPercent, keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage(removeImageId);
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri, offsetXPercent,
//						offsetYPercent - (1 - floatOffset * i), widthPercent, heightPercent, keepAspectRatio,
//						imageCenter);
//			} else if (i == offset) {
//				imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent,
//						offsetYPercent - (1 - floatOffset * i), widthPercent, heightPercent, keepAspectRatio,
//						imageCenter);
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//			} else {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri, offsetXPercent,
//						offsetYPercent + floatOffset * i, widthPercent, heightPercent, keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri, offsetXPercent,
//						offsetYPercent - (1 - floatOffset * i), widthPercent, heightPercent, keepAspectRatio,
//						imageCenter);
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//			}
//		}
//	}
//
//	private void moveRight() {
//		String removeImageId = "testImage" + imageIndex;
//		String removeImageUri = imageUris[imageIndex];
//
//		imageIndex++;
//		String addImageId = "testImage" + imageIndex;
//		String addImageUri = imageUris[imageIndex];
//		int offset = 10;
//		float floatOffset = 1.0f / offset;
//		for (int i = 1; i <= offset; i++) {
//			if (i == 1) {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri,
//						offsetXPercent + floatOffset * i, offsetYPercent, widthPercent, heightPercent, keepAspectRatio,
//						imageCenter);
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri,
//						offsetXPercent - (1 - floatOffset * i), offsetYPercent, widthPercent, heightPercent,
//						keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage(removeImageId);
//			} else if (i == 10) {
//				imageOverlayFilter.addImage(addImageId, addImageUri, offsetXPercent - (1 - floatOffset * i),
//						offsetYPercent, widthPercent, heightPercent, keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//			} else {
//				imageOverlayFilter.addImage("removeAnimationImage" + i, removeImageUri,
//						offsetXPercent + floatOffset * i, offsetYPercent, widthPercent, heightPercent, keepAspectRatio,
//						imageCenter);
//				imageOverlayFilter.addImage("addAnimationImage" + i, addImageUri,
//						offsetXPercent - (1 - floatOffset * i), offsetYPercent, widthPercent, heightPercent,
//						keepAspectRatio, imageCenter);
//				imageOverlayFilter.removeImage("removeAnimationImage" + (i - 1));
//				imageOverlayFilter.removeImage("addAnimationImage" + (i - 1));
//			}
//		}
//	}

	public void full() {
		if (isFullScreen) {
			offsetXPercent = originOffsetXPercent;
			offsetYPercent = originOffsetYPercent;
			widthPercent = originwidthPercent;
			heightPercent = originheightPercent;
			isFullScreen = false;
		} else {
			offsetXPercent = 0.0f;
			offsetYPercent = 0.0f;
			widthPercent = 1.0f;
			heightPercent = 1.0f;
			isFullScreen = true;
		}

		log.info("[full toggle] isFullScreen: {}", isFullScreen);

		String imageId = "testImage" + imageIndex;
		String imageUri = imageUris[imageIndex];
		imageOverlayFilter.removeImage(imageId);
		imageOverlayFilter.addImage(imageId, imageUri, offsetXPercent, offsetYPercent, widthPercent, heightPercent,
				keepAspectRatio, imageCenter);

	}
}
