package renderer;

import base.GameObject;

public class Image extends GameObject {
    public Image(){
        this.renderer = new ImageRenderer("resources/images/start.png",513,84);
    }
}
