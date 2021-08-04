function addImage(){
    console.log("add image")
    var message = {
            id : 'addImage'
        }
    sendMessage(message);
}
    
function removeImage(){
    console.log("remove image")
    var message = {
            id : 'removeImage'
        }
    sendMessage(message);
}

function previousImage(){
    console.log("previous image")
    var message = {
             id : 'previousImage'
        }
    sendMessage(message);
}

function nextImage(){
    console.log("next image")
    var message = {
            id : 'nextImage'
        }
    sendMessage(message);
}
