package com.ssafy.api.service;


import com.ssafy.common.util.Presentation;
import com.ssafy.common.util.Room;
import com.ssafy.common.util.UserSession;
import org.apache.catalina.Pipeline;
import org.kurento.client.ImageOverlayFilter;
import org.kurento.client.MediaPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PresentationManager {
    private final Logger log = LoggerFactory.getLogger(RoomManager.class);

    private final ConcurrentMap<String, Presentation> presentations = new ConcurrentHashMap<>();
    private String[] imageUris={
            "/home/ubuntu/presentations/demo/bird.jpg",
            "/home/ubuntu/presentations/demo/cat.jpg",
            "/home/ubuntu/presentations/demo/cat2.jpg",
            "/home/ubuntu/presentations/demo/flower.jpg",
            "/home/ubuntu/presentations/demo/pets.jpg"
    };
    private int imageIndex;
    private Presentation presentation;
    private ImageOverlayFilter imageOverlayFilter;
    private UserSession user;

    //ImageOverlayFilter 위치, 크기 설정 변수
    private float offsetXPercent = 0.05f;
    private float offsetYPercent = 0.25f;
    private float widthPrecent = 0.64f;
    private float heightPrecent = 0.36f;
    private boolean keepAspectRatio = false;
    private boolean imageCenter = true;
    private boolean isFullScreen = false;


    public Presentation getPresentation(String presenterName, Room room, UserSession user){
        String key=room.getName()+"-"+presenterName;
        presentation=presentations.get(key);

        MediaPipeline pipeline=room.getPipeline();
        if(presentation==null){
            presentation=new Presentation(presenterName, imageUris, room.getName(), pipeline);
            presentations.put(key, presentation);
            this.user=user;

        }
        return presentation;
    }

    public void start(){
        imageIndex=0;
        String imageId = "presentation" +imageIndex;
        String imageUri = imageUris[imageIndex];
        imageOverlayFilter=new ImageOverlayFilter.Builder(presentation.getPipeline()).build();
        imageOverlayFilter.addImage(imageId, imageUri, offsetXPercent, offsetYPercent, widthPrecent, heightPrecent, keepAspectRatio, imageCenter);
        log.info("[start] imageId: {}, imageUri: {}", imageId, imageUri);

       // user.setPresenter();
       // user.linkImageOverlayPipeline(user, imageOverlayFilter);
    }
}
