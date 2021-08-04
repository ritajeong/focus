package com.ssafy.common.util;

import org.kurento.client.MediaPipeline;

public class Presentation {
    private String presenter;
    private String[] presentationImageUris;
    private String roomName;
    private MediaPipeline pipeline;

    public Presentation(String presenter, String[] imageUris, String roomName, MediaPipeline pipeline){
        this.presenter=presenter;
        this.presentationImageUris=imageUris;
        this.roomName=roomName;
        this.pipeline=pipeline;
    }

    public String getPresenterName(){
        return presenter;
    }

    public String[] getPresentationImages(){
        return presentationImageUris;
    }

    public MediaPipeline getPipeline(){
        return pipeline;
    }
}
